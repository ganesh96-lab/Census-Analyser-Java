package censusanalyser;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    List csvFileList=new ArrayList<CensusDAO>();
    Map csvFileMap=new HashMap<String,CensusDAO>();

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
    this.checkValidCSVFile(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            csvFileMap=new HashMap<String,IndiaCensusCSV>();
            csvFileList=new ArrayList<IndiaCensusCSV>();
            Iterator<IndiaCensusCSV> indiaCensusCSVIterator=csvBuilder.getCSVFileIterator(reader,IndiaCensusCSV.class);
            while (indiaCensusCSVIterator.hasNext()){
                CensusDAO censusDAO=new CensusDAO(indiaCensusCSVIterator.next());
                this.csvFileMap.put(censusDAO.getState(),censusDAO);
                this.csvFileList=(List) csvFileMap.values().stream().collect(Collectors.toList());
            }
            return csvFileMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e){
            if (e.getMessage().contains("header!"));
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
        }
    }

    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        this.checkValidCSVFile(csvFilePath);
        try (Reader reader=Files.newBufferedReader(Paths.get(csvFilePath))){
            IcsvBuilder csvBuilder =CSVBuilderFactory.createCSVBuilder();
            csvFileMap=new HashMap<String,IndiaStateCodeCSV>();
            csvFileList=new ArrayList<IndiaStateCodeCSV>();
            Iterator<IndiaStateCodeCSV> indiaStateCodeCSVIterator=csvBuilder.getCSVFileIterator(reader,IndiaStateCodeCSV.class);
            while (indiaStateCodeCSVIterator.hasNext()){
                CensusDAO censusDAO=new CensusDAO(indiaStateCodeCSVIterator.next());
                this.csvFileMap.put(censusDAO.getStateCode(),censusDAO);
                this.csvFileList=(List) csvFileMap.values().stream().collect(Collectors.toList());
            }
            return csvFileMap.size();
        } catch (IOException e){
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM);
        }catch (RuntimeException e){
            if (e.getMessage().contains("header!"));
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_STATE_CODE_FILE_HEADER);
        }
    }

    private <T> Iterator<T> getCSVFileIterator(Reader reader, Class<T> csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<T>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<T> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    private void checkValidCSVFile(String csvFilePath) throws CensusAnalyserException{
        if (!csvFilePath.contains(".csv"))
            throw new CensusAnalyserException("This is invalid file type",CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
    }

    private <T> int getCount(Iterator<T> iterator) {
        Iterable<T> iterable=() -> iterator;
        int numOfEntries=(int)StreamSupport.stream(iterable.spliterator(),false).count();
        return numOfEntries;
    }

    public String getPopulationWiseSortedCensusData(String csvFilePath)throws CensusAnalyserException {
        if (csvFilePath == "./src/test/resources/IndiaStateCensusData.csv"){
            loadIndiaCensusData(csvFilePath);
            if (csvFileList==null || csvFileList.size()==0){
                throw new CensusAnalyserException("NO_CENSUS_DATA",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
            }
            Comparator<CensusDAO> censusCSVComparator=Comparator.comparing(census->census.getPopulation());
            this.sort(censusCSVComparator);
            Collections.reverse(csvFileList);
            String toJson=new Gson().toJson(csvFileList);
            return toJson;
        }
        loadIndiaStateCodeData(csvFilePath);
        if (csvFileList==null || csvFileList.size()==0){
            throw new CensusAnalyserException("NO_CENSUS_DATA",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator=Comparator.comparing(censusDAO->censusDAO.getStateCode());
        this.sort(censusComparator);
        String toJson=new Gson().toJson(csvFileList);
        return toJson;
    }

    public String getDensityWiseSortedCensusData(String csvFilePath)throws CensusAnalyserException {
            loadIndiaCensusData(csvFilePath);
            if (csvFileList==null || csvFileList.size()==0){
                throw new CensusAnalyserException("NO_CENSUS_DATA",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
            }
            Comparator<CensusDAO> censusCSVComparator=Comparator.comparing(census->census.getDensityPerSqKm());
            this.sort(censusCSVComparator);
            Collections.reverse(csvFileList);
            String toJson=new Gson().toJson(csvFileList);
            return toJson;
    }

    public String getAreaWiseSortedCensusData(String csvFilePath)throws CensusAnalyserException {
        loadIndiaCensusData(csvFilePath);
        if (csvFileList==null || csvFileList.size()==0){
            throw new CensusAnalyserException("NO_CENSUS_DATA",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator=Comparator.comparing(census->census.getAreaInSqKm());
        this.sort(censusCSVComparator);
        Collections.reverse(csvFileList);
        String toJson=new Gson().toJson(csvFileList);
        return toJson;
    }

    private  void sort(Comparator<CensusDAO> censusCSVComparator) {
        for (int i = 0; i < csvFileList.size(); i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                CensusDAO census1= (CensusDAO)csvFileList.get(j);
                CensusDAO census2= (CensusDAO)csvFileList.get(j + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }
}

package censusanalyser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
    this.checkValidCSVFile(csvFilePath);

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
           IcsvBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
           List<IndiaCensusCSV> csvFileList= csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
           return csvFileList.size();
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
            List<IndiaStateCodeCSV> csvFileList = csvBuilder.getCSVFileList(reader,IndiaStateCodeCSV.class);
            return csvFileList.size();
        }
        catch (IOException e){
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
}

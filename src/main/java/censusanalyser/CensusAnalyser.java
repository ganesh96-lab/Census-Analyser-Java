package censusanalyser;

/*import com.csvbuilder.CSVBuilderException;
import com.csvbuilder.IcsvBuilder;*/
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static censusanalyser.CSVBuilderFactory.createCSVBuilder;

public class CensusAnalyser<E> {

    public enum Country {
        INDIA,
        US;
    }

    private Country country;

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public CensusAnalyser() {
    }

    List csvFileList =new ArrayList<CensusDAO>();
    Map<String, CensusDAO> csvFileMap = new HashMap<>();

    public int loadCensusData(Country country, String csvFilePath) throws CensusAnalyserException {
        csvFileMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        csvFileList = (List) csvFileMap.values().stream().collect(Collectors.toList());
        return csvFileMap.size();
    }

    public  String getStateNameWiseSortedCensusData(Country country, String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country, csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.getState());
        this.sort(censusComparator);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    public  String getPopulationWiseSortedCensusData(Country country, String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country, csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.getPopulation());
        this.sort(censusComparator);
        Collections.reverse(csvFileList);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    public  String getDensityWiseSortedCensusData(Country country, String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country, csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.getDensityPerSqKm());
        this.sort(censusComparator);
        Collections.reverse(csvFileList);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    public  String getAreaWiseSortedCensusData(Country country, String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country, csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.getAreaInSqKm());
        this.sort(censusComparator);
        Collections.reverse(csvFileList);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    private void sort(Comparator<CensusDAO> censusComparator) {
        for (int i = 0; i < csvFileList.size(); i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                CensusDAO census1 = (CensusDAO) csvFileList.get(j);
                CensusDAO census2 = (CensusDAO) csvFileList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }

            }

        }
    }
}

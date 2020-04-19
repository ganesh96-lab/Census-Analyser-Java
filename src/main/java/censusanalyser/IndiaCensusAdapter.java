package censusanalyser;
/*
import com.csvbuilder.CSVBuilderException;
import com.csvbuilder.IcsvBuilder;
import com.csvbuilder.OpenCSVBuilder;*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter{

    public IndiaCensusAdapter() {
    }

    @Override
    public Map<String, CensusDAO> loadCensusData(String csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> csvFileMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath);
        if (csvFilePath.contains("IndiaStateCensusData.csv"))
            return csvFileMap;
        return loadStateCodeCSVData(csvFileMap, csvFilePath);
    }

    public Map<String, CensusDAO> loadStateCodeCSVData(Map<String, CensusDAO> csvFileMap, String csvFilePath) throws CensusAnalyserException {
        String extension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!extension.equals("csv")) {
            throw new CensusAnalyserException("Incorrect File Type", CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            IcsvBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CensusDAO> stateCodeIterator = icsvBuilder.getCSVFileIterator(reader, CensusDAO.class);
            Iterable<CensusDAO> stateCodes = () -> stateCodeIterator;
            StreamSupport.stream(stateCodes.spliterator(), false)
                    .filter(indiaStateCodeCSV -> csvFileMap.get(indiaStateCodeCSV.getState()) != null)
                    .forEach(indiaStateCodeCSV -> csvFileMap.get(indiaStateCodeCSV.getState()).setStateCode(indiaStateCodeCSV.getStateCode()));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("header!"))
                throw new CensusAnalyserException("Invalid Header", CensusAnalyserException.ExceptionType.WRONG_FILE_HEADER);
            throw new CensusAnalyserException("Invalid Delimiters", CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER);
        } catch (FileNotFoundException e) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IOException e) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return csvFileMap;
    }
}
package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static censusanalyser.CensusAnalyser.Country.INDIA;
import static censusanalyser.CensusAnalyser.Country.US;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/CensusDataTypeWrong.txt";
    private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/CensusDataDelimiterWrong.csv";
    private static final String WRONG_CSV_FILE_HEADER = "./src/test/resources/CensusDataHeaderWrong.csv";

    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String CODE_WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndiaStateCodeDelimiterWrong.csv";

    private static final String US_CENSUS_CSV_FILE_PATH = "src/test/resources/USCensusFile.csv";


    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WithWrongFileHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_HEADER, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFile_ShouldReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenIndiaStateCodeCSV_WhenWrongPath_ShouldReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSV_WhenWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSV_WhenWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSV_WhenWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_withRandomStateNames_ShouldReturnInPopulationSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println(sortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, censusCSV[0].getPopulation());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_withRandomStateNames_ShouldReturnInDensitySortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String densityWiseSortedCensusData = censusAnalyser.getDensityWiseSortedCensusData(INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println(densityWiseSortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(densityWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, censusCSV[0].getDensityPerSqKm());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_withRandomStateNames_ShouldReturnInAreaSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String areaWiseSortedCensusData = censusAnalyser.getAreaWiseSortedCensusData(INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println(areaWiseSortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(areaWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239, censusCSV[0].getAreaInSqKm());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenUSCensusCSVFile_ShouldReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusCSVFile_withRandomStateNames_ShouldReturnInNameSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String stateNameWiseSortedCensusData = censusAnalyser.getStateNameWiseSortedCensusData(US, US_CENSUS_CSV_FILE_PATH);
            System.out.println(stateNameWiseSortedCensusData);
            USCensusCSV[] censusCSV = new Gson().fromJson(stateNameWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCSV[0].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }
}
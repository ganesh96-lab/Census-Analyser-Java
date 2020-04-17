package censusanalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/CensusData.text";
    private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/CensusDelimiter.csv";
    private static final String WRONG_CSV_FILE_HEADER = "./src/test/resources/CensusHeaderChange.csv";

    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCode.tet";
    private static final String WRONG_STATE_CODE_FILE_DELIMITER = "./src/test/resources/IndiaStateCodeDelimiter.csv";
    private static final String WRONG_STATE_CODE_FILE_HEADER = "./src/test/resources/StateCodeHeaderChange.csv";

    private static final String US_CENSUS_CSV_FILE_PATH = "src/test/resources/USCensusFile.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithCorrectFile_incorrectType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithCorrectFile_IncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithCorrectFile_IncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER,e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
        } catch (CensusAnalyserException e1) { }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithCorrectFile_incorrectType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE,e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithCorrectFile_IncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithCorrectFile_IncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_STATE_CODE_FILE_HEADER,e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSV_withRandomStateNames_ShouldReturnInStateNameSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String stateWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(INDIA_STATE_CODE_CSV_FILE_PATH);
            System.out.println(stateWiseSortedCensusData);
            IndiaStateCodeCSV[] indiaStateCodeCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaStateCodeCSV[].class);
            Assert.assertEquals("AD", indiaStateCodeCSV[0].StateCode);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }

    }

    @Test
    public void givenIndianCensusData_withRandomStateNames_ShouldReturnInStateNamePopulationWiseSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String PopulationWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println(PopulationWiseSortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(PopulationWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, censusCSV[0].population);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_withRandomStateNames_ShouldReturnInDensitySortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String densityWiseSortedCensusData = censusAnalyser.getDensityWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println("densitywise="+densityWiseSortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(densityWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, censusCSV[0].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_withRandomStateNames_ShouldReturnInByAreaSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String areaWiseSortedCensusData = censusAnalyser.getAreaWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println("Areawise="+areaWiseSortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(areaWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239, censusCSV[0].areaInSqKm);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenUSCensusCSVFile_ShouldReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

   @Test
    public void givenUSCensusData_withRandomStateNames_ShouldReturnInStateNamePopulationWiseSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String PopulationWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(US_CENSUS_CSV_FILE_PATH);
            System.out.println(PopulationWiseSortedCensusData);
            USCensusCSV[] censusCSV = new Gson().fromJson(PopulationWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(4779736, censusCSV[0].getPopulation());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_US_CENSUS_DATA, e.type);
        }
    }
}

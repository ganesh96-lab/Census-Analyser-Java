package censusanalyser;

public class CensusAnalyserException extends Exception {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM,
        INVALID_FILE_HEADER,
        STATE_CODE_FILE_PROBLEM,
        INVALID_STATE_CODE_FILE_HEADER,
        WRONG_FILE_TYPE,
        UNABLE_TO_PARSE,
        NO_CENSUS_DATA;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type =type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}

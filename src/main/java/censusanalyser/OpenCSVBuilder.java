package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder implements IcsvBuilder{
      @Override
    public <T> List<T> getCSVFileList(Reader reader, Class<T> csvClass) throws CensusAnalyserException {
        new CsvToBeanBuilder(reader);
        return this.getCSVToBean(reader, csvClass).parse();
    }


    private <T> CsvToBean<T> getCSVToBean(Reader reader, Class<T> csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<T>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}


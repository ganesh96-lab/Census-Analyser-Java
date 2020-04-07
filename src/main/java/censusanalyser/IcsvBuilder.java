package censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface IcsvBuilder {
    public <T>Iterator<T> getCSVFileIterator(Reader reader,Class<T> csvClass)throws CensusAnalyserException;
}

package censusanalyser;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IcsvBuilder {
    <T>Iterator<T> getCSVFileIterator(Reader reader, Class<T> csvClass)throws  CensusAnalyserException;

    <T> List<T> getCSVFileList(Reader reader, Class<T> csvClass) throws CensusAnalyserException;
}

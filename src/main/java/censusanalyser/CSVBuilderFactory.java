package censusanalyser;


/*import com.csvbuilder.IcsvBuilder;
import com.csvbuilder.OpenCSVBuilder;*/

public class CSVBuilderFactory {
    public static IcsvBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}
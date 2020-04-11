package censusanalyser;

import com.bl.csvbuilder.OpenCSVBuilder;

public class CSVBuilderFactory {
    public static OpenCSVBuilder createCSVBuilder(){
        return new OpenCSVBuilder();
    }
}

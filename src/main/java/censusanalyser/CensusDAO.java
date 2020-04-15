package censusanalyser;

public class CensusDAO {
    public String state;
    public int population;
    public int areaInSqKm;
    public int densityPerSqKm;
    public String stateCode;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV){
        this.state=indiaCensusCSV.state;
        this.population=indiaCensusCSV.population;
        this.areaInSqKm=indiaCensusCSV.areaInSqKm;
        this.densityPerSqKm=indiaCensusCSV.densityPerSqKm;
    }
public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV){
        this.stateCode=indiaStateCodeCSV.StateCode;
}
}

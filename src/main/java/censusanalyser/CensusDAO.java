package censusanalyser;

public class CensusDAO {
    private String srNo;
    private String state;
    private int population;
    private int areaInSqKm;
    private int densityPerSqKm;
    private String stateCode;
    private String tin;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV){
        this.state=indiaCensusCSV.state;
        this.population=indiaCensusCSV.population;
        this.areaInSqKm=indiaCensusCSV.areaInSqKm;
        this.densityPerSqKm=indiaCensusCSV.densityPerSqKm;
    }

    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV){
        this.srNo=indiaStateCodeCSV.SrNo;
        this.state=indiaStateCodeCSV.StateName;
        this.stateCode=indiaStateCodeCSV.StateCode;
        this.tin=indiaStateCodeCSV.TIN;
    }

    public String getSrNo(){
        return srNo;
    }

    public void  setSrNo(String srNo){
        this.srNo=srNo;
    }

    public String getState(){
        return state;
    }

    public  void setState(String state){
        this.state=state;
    }

    public int getPopulation(){
        return population;
    }

    public void setPopulation(int population){
        this.population=population;
    }

    public void setAreaInSqKm(int areaInSqKm){
        this.areaInSqKm=areaInSqKm;
    }

    public int getAreaInSqKm(){
        return areaInSqKm;
    }

    public int getDensityPerSqKm(){
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(int densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }


}

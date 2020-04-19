package censusanalyser;

public class CensusDAO {
    private String srNo;
    private String state;
    private int population;
    private double areaInSqKm;
    private double densityPerSqKm;
    private String stateCode;
    private String tin;
    private String stateID;
    private double housingDensity;

    public CensusDAO() {
    }

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        this.state = indiaCensusCSV.getState();
        this.population = indiaCensusCSV.getPopulation();
        this.areaInSqKm = indiaCensusCSV.getAreaInSqKm();
        this.densityPerSqKm = indiaCensusCSV.getDensityPerSqKm();
    }

    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        this.srNo = indiaStateCodeCSV.getSrNo();
        this.state = indiaStateCodeCSV.getStateName();
        this.stateCode = indiaStateCodeCSV.getStateCode();
        this.tin = indiaStateCodeCSV.getTin();
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        this.state = usCensusCSV.getState();
        this.population = usCensusCSV.getPopulation();
        this.areaInSqKm = usCensusCSV.getArea();
        this.densityPerSqKm = usCensusCSV.getPopulationDensity();
        this.stateID = usCensusCSV.getStateID();
        this.housingDensity = usCensusCSV.getHousingDensity();
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public double getHousingDensity() {
        return housingDensity;
    }

    public void setHousingDensity(double housingDensity) {
        this.housingDensity = housingDensity;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(int areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public double getDensityPerSqKm() {
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
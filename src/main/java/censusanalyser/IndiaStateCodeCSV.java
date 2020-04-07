package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

    //SrNo,StateName,TIN,StateCode,
    @CsvBindByName(column = "SrNo", required = true)
    public String SrNo;

    @CsvBindByName(column = "State Name", required = true)
    public String StateName;

    @CsvBindByName(column = "TIN", required = true)
    public String TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;
    //SrNo,State Name,TIN,StateCode
    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "SrNo='" + SrNo + '\'' +
                "State Name='"+StateName+'\''+
                ", TIN='" + TIN + '\'' +
                ", StateCode='" + StateCode + '\'' +
                '}';
    }

}

package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoPiece {
     public  String CountryCode;
     public  String CountryName;
     public  String Continent;
     public  Date date;
     public  Long newCases;
     public  Long totCases;
     public  Long newDeaths;
     public  Long totDeaths;
     public  Long newVACs;
     public  Long totVACs;


    public InfoPiece(String countryCode, String countryName, String continent, String date, String newCases, String totCases, String newDeaths, String totDeaths, String newVACs, String totVACs) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CountryCode = countryCode;
        CountryName = countryName;
        Continent = continent;
        this.date = sdf.parse(date);
        this.newCases =(long) Double.parseDouble(newCases);
        this.totCases =(long) Double.parseDouble(totCases);
        this.newDeaths =(long) Double.parseDouble(newDeaths);
        this.totDeaths =(long) Double.parseDouble (totDeaths);
        this.newVACs = (long) Double.parseDouble(newVACs);
        this.totVACs = (long) Double.parseDouble(totVACs);
    }

    public InfoPiece(String countryCode, String countryName, String continent, Date date, long newCases, long totCases, long newDeaths, long totDeaths, long newVACs, long totVACs) {
        this.CountryCode = countryCode;
        CountryName = countryName;
        Continent = continent;
        this.date = date;
        this.newCases = newCases;
        this.totCases = totCases;
        this.newDeaths = newDeaths;
        this.totDeaths = totDeaths;
        this.newVACs = newVACs;
        this.totVACs = totVACs;
    }

    @Override
    public String toString() {
        return "InfoPiece{" +
                "CountryCode='" + CountryCode + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", Continent='" + Continent + '\'' +
                ", date=" + date +
                ", newCases=" + newCases +
                ", totCases=" + totCases +
                ", newDeaths=" + newDeaths +
                ", totDeaths=" + totDeaths +
                ", newVACs=" + newVACs +
                ", totVACs=" + totVACs +
                '}';
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getNewCases() {
        return newCases;
    }

    public void setNewCases(Long newCases) {
        this.newCases = newCases;
    }

    public Long getTotCases() {
        return totCases;
    }

    public void setTotCases(Long totCases) {
        this.totCases = totCases;
    }

    public Long getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Long newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Long getTotDeaths() {
        return totDeaths;
    }

    public void setTotDeaths(Long totDeaths) {
        this.totDeaths = totDeaths;
    }

    public Long getNewVACs() {
        return newVACs;
    }

    public void setNewVACs(Long newVACs) {
        this.newVACs = newVACs;
    }

    public Long getTotVACs() {
        return totVACs;
    }

    public void setTotVACs(Long totVACs) {
        this.totVACs = totVACs;
    }
}

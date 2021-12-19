package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoPiece {
    public String countryCode;
    public String countryName;
    public String continent;
    public Date date;
    public Long newCases;
    public Long totCases;
    public Long newDeaths;
    public Long totDeaths;
    public Long newVACs;
    public Long totVACs;


    public InfoPiece(String countryCode, String countryName, String continent, String date, String newCases, String totCases, String newDeaths, String totDeaths, String newVACs, String totVACs) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.continent = continent;
        this.date = sdf.parse(date);
        this.newCases = (long) translate(newCases);
        this.totCases = (long) translate(totCases);
        this.newDeaths = (long) translate(newDeaths);
        this.totDeaths = (long) translate(totDeaths);
        this.newVACs = (long) translate(newVACs);
        this.totVACs = (long) translate(totVACs);
    }

    public InfoPiece(String countryCode, String countryName, String continent, Date date, long newCases, long totCases, long newDeaths, long totDeaths, long newVACs, long totVACs) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.continent = continent;
        this.date = date;
        this.newCases = newCases;
        this.totCases = totCases;
        this.newDeaths = newDeaths;
        this.totDeaths = totDeaths;
        this.newVACs = newVACs;
        this.totVACs = totVACs;
    }


    private double translate(String num){
        double ans;
        try {
            ans = Double.parseDouble(num);
        }catch (NumberFormatException ne){
            ans = 0;
        }
        return ans;
    }
    @Override
    public String toString() {
        return "InfoPiece{" +
                "CountryCode='" + countryCode + '\'' +
                ", CountryName='" + countryName + '\'' +
                ", Continent='" + continent + '\'' +
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
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
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

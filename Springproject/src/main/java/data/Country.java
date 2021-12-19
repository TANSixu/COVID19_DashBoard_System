package data;

import java.util.ArrayList;

public class Country {
    private String CountryCode;
    private String CountryName;
    private String Continent;
    public ArrayList<InfoPiece> infoList;
    private int total_cases;
    private int total_deaths;
    private int total_Vacs;


    public Country(String countryCode, String countryName, String continent) {
        CountryCode = countryCode;
        CountryName = countryName;
        Continent = continent;
        infoList = new ArrayList<>();
    }

    public int getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(int total_cases) {
        this.total_cases = total_cases;
    }

    public int getTotal_deaths() {
        return total_deaths;
    }

    public void setTotal_deaths(int total_deaths) {
        this.total_deaths = total_deaths;
    }

    public int getTotal_Vacs() {
        return total_Vacs;
    }

    public void setTotal_Vacs(int total_Vacs) {
        this.total_Vacs = total_Vacs;
    }
    public void add_info(InfoPiece infoPiece){
        infoList.add(infoPiece);
    }
    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
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
}

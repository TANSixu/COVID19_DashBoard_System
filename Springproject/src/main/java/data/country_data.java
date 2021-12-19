package data;

import java.util.ArrayList;

public class country_data {
    ArrayList<String> date;
    ArrayList<Long> total_deaths;
    ArrayList<Long>  total_Vacs;
    ArrayList<Long>  total_cases;
    country_data(){
        this.date = new ArrayList<>();
        this.total_deaths = new ArrayList<>();
        this.total_Vacs = new ArrayList<>();
        this.total_cases = new ArrayList<>();
    }
}

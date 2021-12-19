package data;

import com.google.gson.Gson;


import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class dataGet {
    public static String continent_data_get(country_case[] country_cases ){
        //从数据库中找到每个国家最新的info 在完成 country_cases的实例
        continent Asia = new continent("Asia",0);
        continent Europe = new continent("Europe",0);
        continent Africa = new continent("Africa",0);
        continent South_America = new continent("South America",0);
        continent North_America = new continent("North America",0);
        continent Oceania = new continent("Oceania",0);
        continent [] continents = {South_America,North_America,Africa,Asia,Europe,Oceania};
        for (int i = 0; i <country_cases.length ; i++) {
            for (int j = 0; j <continents.length ; j++) {
                if(country_cases[i].Continent.equals(continents[j].name)){
                    continents[j].value = continents[j].value + country_cases[i].value;
                    break;
                }
            }
        }
        Gson gson = new Gson();
        String data ="" ;
        data =gson.toJson(continents);
        return data;

    }

    public static String country_data_get(Country country, Date date) throws ParseException {
        // 从数据库中找到这个country所有信息
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,6);
        ArrayList<InfoPiece> infoPieces = new ArrayList<>();

        Date date_add_6 = calendar.getTime();

        for (int i = 0; i < country.infoList.size() ; i++) {
            if (!country.infoList.get(i).date.after(date_add_6)&&!country.infoList.get(i).date.before(date)){
                infoPieces.add(country.infoList.get(i));
            }
        }

        Collections.sort(infoPieces,new Comparator<InfoPiece>() {
            @Override
            public int compare(InfoPiece f1, InfoPiece f2) {
                return f1.date.compareTo(f2.date);
            }
        });
        country_data country_data = new country_data();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < infoPieces.size(); i++) {
            country_data.date.add(simpleDateFormat.format(infoPieces.get(i).date));
            country_data.total_deaths.add( infoPieces.get(i).newDeaths);
            country_data.total_cases.add(infoPieces.get(i).newCases);
            if(infoPieces.get(i).totVACs==-1){infoPieces.get(i).newVACs= Long.valueOf(0);}
            country_data.total_Vacs.add(infoPieces.get(i).newVACs);
        }

        Gson gson = new Gson();
        String data ="";
        data= gson.toJson(country_data);


        return data;
    }

    public static String dynamicData_get(ArrayList<InfoPiece> records){
        // 数据库中找到十个最多case的国家，把这些国家的所有info放到records
       String data = "";

        Collections.sort(records,new Comparator<InfoPiece>() {
            @Override
            public int compare(InfoPiece f1, InfoPiece f2) {
                return f1.date.compareTo(f2.date);
            }
        });
        ArrayList<dynamicData> dynamicDataArrayList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        records.forEach(infoPiece -> {
            dynamicData dynamicData = new dynamicData(simpleDateFormat.format(infoPiece.date),infoPiece.CountryName,infoPiece.totCases);
            dynamicDataArrayList.add(dynamicData);
        });

        Gson gson = new Gson();
        data = gson.toJson(dynamicDataArrayList);

        return data;
    }

    public static String map_data_get(country_case[] country_cases ){
        Gson gson = new Gson();
        String data= "";
        for (int i = 0; i < country_cases.length; i++) {
            country_cases[i].Continent = null;
        }
        data = gson.toJson(country_cases);
        return data;
    }

    public static String table_data_get(ArrayList<InfoPiece> records){
        //从数据库把所有的info 全部选出
        for (int i = 0; i < records.size(); i++) {
            records.get(i).Continent=null;
            records.get(i).CountryCode = null;
            records.get(i).date = null;
        }
        Gson gson = new Gson();
        String data = "";
        data = gson.toJson(records);
        return data;
    }

}

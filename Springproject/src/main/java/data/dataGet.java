package data;

import com.google.gson.Gson;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DataGet {
    public static String continentDataGet(CountryCase[] country_cases) {
        //从数据库中找到每个国家最新的info 在完成 country_cases的实例
        Continent Asia = new Continent("Asia", 0);
        Continent Europe = new Continent("Europe", 0);
        Continent Africa = new Continent("Africa", 0);
        Continent South_America = new Continent("South America", 0);
        Continent North_America = new Continent("North America", 0);
        Continent Oceania = new Continent("Oceania", 0);
        Continent[] continents = {South_America, North_America, Africa, Asia, Europe, Oceania};
        for (int i = 0; i < country_cases.length; i++) {
            for (int j = 0; j < continents.length; j++) {
                if (country_cases[i].Continent.equals(continents[j].name)) {
                    continents[j].value = continents[j].value + country_cases[i].value;
                    break;
                }
            }
        }
        Gson gson = new Gson();
        String data = "";
        data = gson.toJson(continents);
        return data;

    }

    public static String countryDataGet(Country country, Date date) throws ParseException {
        // 从数据库中找到这个country所有信息
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 6);
        ArrayList<InfoPiece> infoPieces = new ArrayList<>();

        Date date_add_6 = calendar.getTime();

        for (int i = 0; i < country.infoList.size(); i++) {
            if (!country.infoList.get(i).date.after(date_add_6) && !country.infoList.get(i).date.before(date)) {
                infoPieces.add(country.infoList.get(i));
            }
        }

        Collections.sort(infoPieces, new Comparator<InfoPiece>() {
            @Override
            public int compare(InfoPiece f1, InfoPiece f2) {
                return f1.date.compareTo(f2.date);
            }
        });
        CountryData country_data = new CountryData();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < infoPieces.size(); i++) {
            country_data.date.add(simpleDateFormat.format(infoPieces.get(i).date));
            country_data.total_deaths.add(infoPieces.get(i).newDeaths);
            country_data.total_cases.add(infoPieces.get(i).newCases);
            if (infoPieces.get(i).totVACs == -1) {
                infoPieces.get(i).newVACs = Long.valueOf(0);
            }
            country_data.total_Vacs.add(infoPieces.get(i).newVACs);
        }

        Gson gson = new Gson();
        String data = "";
        data = gson.toJson(country_data);


        return data;
    }

    public static String dynamicDataGet(ArrayList<InfoPiece> records) {
        // 数据库中找到十个最多case的国家，把这些国家的所有info放到records
        String data = "";

        Collections.sort(records, new Comparator<InfoPiece>() {
            @Override
            public int compare(InfoPiece f1, InfoPiece f2) {
                return f1.date.compareTo(f2.date);
            }
        });
        ArrayList<DynamicData> dynamicDataArrayList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        records.forEach(infoPiece -> {
            DynamicData dynamicData = new DynamicData(simpleDateFormat.format(infoPiece.date), infoPiece.CountryName, infoPiece.totCases);
            dynamicDataArrayList.add(dynamicData);
        });

        Gson gson = new Gson();
        data = gson.toJson(dynamicDataArrayList);

        return data;
    }

    public static String mapDataGet(CountryCase[] country_cases) {
        Gson gson = new Gson();
        String data = "";
        for (int i = 0; i < country_cases.length; i++) {
            country_cases[i].Continent = null;
        }
        data = gson.toJson(country_cases);
        return data;
    }

    public static String tableDataGet(ArrayList<InfoPiece> records) {
        //从数据库把所有的info 全部选出
        for (int i = 0; i < records.size(); i++) {
            records.get(i).Continent = null;
            records.get(i).CountryCode = null;
            records.get(i).date = null;
        }
        Gson gson = new Gson();
        String data = "";
        data = gson.toJson(records);
        return data;
    }

}

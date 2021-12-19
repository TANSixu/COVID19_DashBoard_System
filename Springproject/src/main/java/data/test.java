package data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import database.DatabaseAccess;

@SuppressWarnings("ALL")
public class test {
    public static void main(String[] args) throws ParseException, NoSuchFieldException {
        ArrayList<InfoPiece> records = TestFileRead.read();
//        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2021-1-20");
//      test_Country(records,"China",date);
//        test_dynamicData(records);
//        test_map(records);
//        test_continent(records);
          test_table(records,"totCases","1",date);
    }
    public static String test_map (ArrayList<InfoPiece> records){
        Date date= records.get(records.size()-1).date;
        ArrayList<InfoPiece> pieces = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = records.size()-1; i >=0 ; i--) {
            if(simpleDateFormat.format(date).equals(simpleDateFormat.format(records.get(i).date))){
                pieces.add(records.get(i));
            }
        }
        country_case[] country_cases = new country_case[pieces.size()];
        TestFileRead.get_country_cases(pieces).toArray(country_cases);
        System.out.println(dataGet.map_data_get(country_cases));
        return dataGet.map_data_get(country_cases);
    }
    public static String test_Country(ArrayList<InfoPiece> records,String CountryName,Date date) throws ParseException {
       ArrayList<InfoPiece> pieces = new ArrayList<>();
        for (int i = 0; i <records.size() ; i++) {
            if(records.get(i).CountryName.equals(CountryName)){
                pieces.add(records.get(i));
            }
        }

        Country country = TestFileRead.get_Country(pieces);
        System.out.println(dataGet.country_data_get(country,date));
        return dataGet.country_data_get(country,date);
    }
    public static String test_dynamicData(ArrayList<InfoPiece> records){
        ArrayList<InfoPiece> pieces = TestFileRead.get_top10_records(records);
        System.out.println(dataGet.dynamicData_get(pieces));
        return dataGet.dynamicData_get(pieces);
     }
    public static String test_continent( ArrayList<InfoPiece> records){
        // test continent
        Date date= records.get(records.size()-1).date;
        ArrayList<InfoPiece> pieces = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = records.size()-1; i >=0 ; i--) {
            if(simpleDateFormat.format(date).equals(simpleDateFormat.format(records.get(i).date))){
                pieces.add(records.get(i));
            }
        }
        country_case[] country_cases = new country_case[pieces.size()];
        TestFileRead.get_country_cases(pieces).toArray(country_cases);
        System.out.println(dataGet.continent_data_get(country_cases));
        return dataGet.continent_data_get(country_cases);
    }
    public static String test_table(ArrayList<InfoPiece> records, String group, String order,Date date) throws NoSuchFieldException {

        ArrayList<InfoPiece> pieces = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = records.size()-1; i >=0 ; i--) {
            if (simpleDateFormat.format(date).equals(simpleDateFormat.format(records.get(i).date))) {
                pieces.add(records.get(i));
            }
        }
        Field tt = InfoPiece.class.getField(group);
        int Order = Integer.parseInt(order);
        //order = 1 升序
        //order = -1 降序
        Collections.sort(pieces,new Comparator<InfoPiece>() {
            @Override
            public int compare(InfoPiece f1, InfoPiece f2) {
                Long a = Long.valueOf(0), b = Long.valueOf(0);
                try {
                    tt.setAccessible(true);
                    a =(Long) tt.get(f1);
                    b = (Long) tt.get(f2);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                return a.compareTo(b)*Order;
            }
        });
        System.out.println(dataGet.table_data_get(pieces));
        return dataGet.table_data_get(pieces);
    }
}

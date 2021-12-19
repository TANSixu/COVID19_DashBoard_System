package data;

import database.DatabaseAccess;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@SuppressWarnings("ALL")
public class DataUtil {
    public static void main(String[] args) throws ParseException, NoSuchFieldException {
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2021-1-20");
//      test_Country(records,"China",date);
//        test_dynamicData(records);
//        test_map(records);
//        test_continent(records);
          tableData(records,"newCases","-1",date);
    }
    public static String mapChartData(ArrayList<InfoPiece> records){
        Date date= records.get(records.size()-1).date;
        ArrayList<InfoPiece> pieces = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = records.size()-1; i >=0 ; i--) {
            if(simpleDateFormat.format(date).equals(simpleDateFormat.format(records.get(i).date))){
                pieces.add(records.get(i));
            }
        }
        CountryCase[] country_cases = new CountryCase[pieces.size()];
        DataReadEncapsulation.getCountryCases(pieces).toArray(country_cases);
        System.out.println(DataToJson.mapDataGet(country_cases));
        return DataToJson.mapDataGet(country_cases);
    }
    public static String lineChartData(ArrayList<InfoPiece> records, String CountryName, Date date) throws ParseException {
       ArrayList<InfoPiece> pieces = new ArrayList<>();
        for (int i = 0; i <records.size() ; i++) {
            if(records.get(i).countryName.equals(CountryName)){
                pieces.add(records.get(i));
            }
        }

        Country country = DataReadEncapsulation.getCountry(pieces);
        System.out.println(DataToJson.countryDataGet(country,date));
        return DataToJson.countryDataGet(country,date);
    }
    public static String animaData(ArrayList<InfoPiece> records){
        ArrayList<InfoPiece> pieces = DataReadEncapsulation.getTop10Records(records);
        System.out.println(DataToJson.dynamicDataGet(pieces));
        return DataToJson.dynamicDataGet(pieces);
     }
    public static String pieChartData(ArrayList<InfoPiece> records){
        // test continent
        Date date= records.get(records.size()-1).date;
        ArrayList<InfoPiece> pieces = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = records.size()-1; i >=0 ; i--) {
            if(simpleDateFormat.format(date).equals(simpleDateFormat.format(records.get(i).date))){
                pieces.add(records.get(i));
            }
        }
        CountryCase[] country_cases = new CountryCase[pieces.size()];
        DataReadEncapsulation.getCountryCases(pieces).toArray(country_cases);
        System.out.println(DataToJson.continentDataGet(country_cases));
        return DataToJson.continentDataGet(country_cases);
    }
    public static String tableData(ArrayList<InfoPiece> records, String group, String order, Date date) throws NoSuchFieldException {

        ArrayList<InfoPiece> pieces = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = records.size()-1; i >=0 ; i--) {
            if (simpleDateFormat.format(date).equals(simpleDateFormat.format(records.get(i).date))) {
                pieces.add(records.get(i));
            }
        }
        Field tt = InfoPiece.class.getField(group);
        int orderValue = Integer.parseInt(order);
        //order = 1 升序
        //order = -1 降序
        Collections.sort(pieces,new Comparator<InfoPiece>() {
            @Override
            public int compare(InfoPiece f1, InfoPiece f2) {
                Object a = Long.valueOf(0), b = Long.valueOf(0);
                try {
                    tt.setAccessible(true);
                    a = tt.get(f1);
                    b = tt.get(f2);
                    tt.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    Method cmp = a.getClass().getDeclaredMethod("compareTo", a.getClass());
                    return (int)cmp.invoke(a, b)*orderValue;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        });
        System.out.println(DataToJson.tableDataGet(pieces));
        return DataToJson.tableDataGet(pieces);
    }
}

package database;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.stream.Collectors;
import data.InfoPiece;

public class Crawler {
    final private static String url = "https://www.worldometers.info/coronavirus/";
//total 2
//new 3
//tot death 4
//new death 5
//totrec 6
//new rec 7
public static void main(String[] args) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    scrape();
    System.out.println(sdf.format(date.getTime()));
}

    public static void scrape() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements facultyList = doc.select("#main_table_countries_today li");
//        Elements rows = doc.getElementsByTag("tr");
        Elements rows = doc.getElementById("main_table_countries_today").getElementsByTag("tr");
        ArrayList<String> lines = new ArrayList<>();

        for (Element e : rows) {
            StringBuilder stringBuilder = new StringBuilder();
            Elements elements = e.getElementsByTag("td");
            for (Element ee : elements) {
                String tmp = ee.text();
                stringBuilder.append(tmp);
                stringBuilder.append("$");
            }
            lines.add(stringBuilder.toString());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String cDate = sdf.format(date.getTime());
        ArrayList<InfoPiece> myInfo = new ArrayList<>();
        lines.stream()
                .map(x -> x.split("\\$"))
                .filter(x -> x.length > 20)
                .forEach(x -> {
                    for (int i = 0; i < x.length; ++i) {
                        if (x[i].equals("")) {
                            x[i] = "0";
                        }
                        x[i] = x[i].replaceAll(",", "");
                    }
                    try {
                        InfoPiece infoPiece = new InfoPiece(x[1], x[1], x[15], cDate, x[3], x[2], x[5], x[4], x[7], x[6]);
                        myInfo.add(infoPiece);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });

        myInfo.stream()
                .forEach(x -> DatabaseAccess.insertPiece(x));

        System.out.println("success!");
    }
}

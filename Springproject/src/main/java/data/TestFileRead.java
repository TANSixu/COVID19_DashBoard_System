package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TestFileRead {
    static String fileName = "E:\\java2\\Springproject\\src\\main\\java\\owid-covid-data.csv";

    public static ArrayList<InfoPiece> getTop10Records(ArrayList<InfoPiece> infoPieces) {
        ArrayList<InfoPiece> pieces = new ArrayList<>();
        String[] countryNames = {"China", "United States", "Russia", "Japan", "South Korea", "Germany", "United Kingdom", "Brazil", "India", "South Africa"};
        for (int i = 0; i < infoPieces.size(); i++) {
            for (int j = 0; j < countryNames.length; j++) {
                if (infoPieces.get(i).countryName.equals(countryNames[j])) {
                    pieces.add(infoPieces.get(i));
                    break;
                }
            }
        }

        return pieces;
    }

    public static ArrayList<CountryCase> getCountryCases(ArrayList<InfoPiece> infoPieces) {
        ArrayList<CountryCase> country_cases = new ArrayList<>();
        for (int i = 0; i < infoPieces.size(); i++) {
            CountryCase country_case = new CountryCase(infoPieces.get(i).countryName, infoPieces.get(i).totCases, infoPieces.get(i).continent);
            country_cases.add(country_case);
        }
        return country_cases;
    }

    public static Country getCountry(ArrayList<InfoPiece> infoPieces) {
        Country country = new Country(infoPieces.get(0).countryCode, infoPieces.get(0).countryName, infoPieces.get(0).continent);
        country.infoList = infoPieces;
        return country;
    }

    public static ArrayList<InfoPiece> read() {
        ArrayList<InfoPiece> records = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.skip(1)
//                    .map(s -> s+",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,")
                    .map(s -> s.split(","))
                    .filter(s -> s.length > 38)
                    .forEach(x -> {
                        for (int i = 0; i < x.length; ++i) {
                            if (x[i].equals("")) {
                                x[i] = "-1";
                            }
                        }
                        try {
                            InfoPiece tmp = new InfoPiece(x[0], x[2], x[1], x[3], x[5],
                                    x[4], x[8], x[7], x[38], x[34]);
                            records.add(tmp);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static ArrayList<InfoPiece> readFromDataset(){
        ArrayList<InfoPiece> records = new ArrayList<>();

        return records;
    }
}

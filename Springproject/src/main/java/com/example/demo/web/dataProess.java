package com.example.demo.web;
import data.InfoPiece;
import data.TestFileRead;
import data.test;
import database.DatabaseAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/data")
public class dataProess {

    @GetMapping("/continent")
    public String continent(){
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        return test.test_continent(records);
    }

    @GetMapping("/country")
    public String country(@RequestParam String countryName,String date) throws ParseException {
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(date);
        return test.test_Country(records,countryName,date1);
    }

    @GetMapping("/map")
    public String map(){
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        return test.test_map(records);
    }

    @GetMapping("/dynamic")
    public String dynamic(){
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        return test.test_dynamicData(records);
    }

    @GetMapping("/table")
    public String table(@RequestParam String date, String group, String order) throws ParseException, NoSuchFieldException {
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(date);
        return test.test_table(records,group,order,date1);
    }

}

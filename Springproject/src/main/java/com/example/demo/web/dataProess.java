package com.example.demo.web;
import data.InfoPiece;
import data.DataUtil;
import database.DatabaseAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/data")
public class dataProess {

    @GetMapping("/continent")
    public String continent(){
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        return DataUtil.pieChartData(records);
    }

    @GetMapping("/country")
    public String country(@RequestParam String countryName,String date) throws ParseException {
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(date);
        return DataUtil.lineChartData(records,countryName,date1);
    }

    @GetMapping("/map")
    public String map(){
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        return DataUtil.mapChartData(records);
    }

    @GetMapping("/dynamic")
    public String dynamic(){
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        return DataUtil.animaData(records);
    }

    @GetMapping("/table")
    public String table(@RequestParam String date, String group, String order) throws ParseException, NoSuchFieldException {
        ArrayList<InfoPiece> records = DatabaseAccess.getAllRecords();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(date);
        return DataUtil.tableData(records,group,order,date1);
    }

}

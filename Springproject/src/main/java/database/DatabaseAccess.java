package database;

import data.InfoPiece;
import database.SQLDataSource;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;


public class DatabaseAccess {
    public static void main(String[] args) {
        ArrayList<InfoPiece> ans = getAllRecords();
        System.out.println("hahah");
    }

    public static void insertPiece(InfoPiece piece) {
        try (Connection connection = SQLDataSource.getInstance().getSQLConnection()) {
            PreparedStatement pstm = connection.prepareStatement("insert into daily_data(country, continent, day, new_case, tot_case, new_death, tot_death, new_vac, tot_vac) VALUES (?,?,?,?,?,?,?,?,?)");
            pstm.setString(1, piece.getCountryName());
            pstm.setString(2, piece.getContinent());
            pstm.setDate(3, new Date(piece.getDate().getTime()));
            pstm.setDouble(4, piece.getNewCases());
            pstm.setDouble(5, piece.getTotCases());
            pstm.setDouble(6, piece.getNewDeaths());
            pstm.setDouble(7, piece.getTotCases());
            pstm.setDouble(8, piece.getNewVACs());
            pstm.setDouble(9, piece.getTotVACs());
            pstm.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<InfoPiece> getRecords(PreparedStatement pstm) throws SQLException, ParseException {
        ArrayList<InfoPiece> myInfo = new ArrayList<>();
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            InfoPiece piece = new InfoPiece("code", rs.getString(1), rs.getString(2), new java.util.Date(rs.getDate(3).getTime()), rs.getLong(4), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9));
            myInfo.add(piece);
        }
        return myInfo;
    }

    public static ArrayList<InfoPiece> getOneCountryRecords(String cName) {
        ArrayList<InfoPiece> myInfo = new ArrayList<>();
        try (Connection connection = SQLDataSource.getInstance().getSQLConnection()) {
            PreparedStatement pstm = connection.prepareStatement("select * from daily_data\n" +
                    "where country = ?");
            pstm.setString(1, cName);
            myInfo = getRecords(pstm);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myInfo;
    }

    public static ArrayList<InfoPiece> getAllRecords() {
        ArrayList<InfoPiece> myInfo = new ArrayList<>();
        try (Connection connection = SQLDataSource.getInstance().getSQLConnection()) {
            PreparedStatement pstm = connection.prepareStatement("select * from daily_data");
            myInfo = getRecords(pstm);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myInfo;
    }


//
//1.一个国家所有记录
//2。 每个国家最新记录
//3. 所有记录
}

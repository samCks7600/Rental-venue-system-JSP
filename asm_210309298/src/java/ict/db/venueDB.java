/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.venueBean1;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sam
 */
public class venueDB {

    private String dburl = "";
    private String dbuser = "";
    private String dbpassword = "";

    public venueDB(String dburl, String dbuser, String dbpassword) {
        this.dburl = dburl;
        this.dbuser = dbuser;
        this.dbpassword = dbpassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(dburl, dbuser, dbpassword);
    }

    public boolean addvenueInfo(String description, String venueName,
           String venueState, String type, String location, String personInChargeId, String bookFee) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        boolean isSuccess = false;
        try {

            connection = getConnection();

            String preQueryStatement
                    = "INSERT INTO `venue` (`venueId`,"
                    + " `Description`,"
                    + " `venueName`,"
                    + " `venueStatus`, "
                    + "`Type`,"
                    + " `Location`,"
                    + " `Person_in_charge_id`,"
                    + " `Bookfee`"
                    + ") VALUES (NULL,?,?,?,?,?,?,?);";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, description);
            pStatement.setString(2, venueName);
            pStatement.setString(3, venueState);
            pStatement.setString(4, type);
            pStatement.setString(5, location);
            pStatement.setString(6, personInChargeId);
            pStatement.setString(7, bookFee);

            int rowCount = pStatement.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStatement.close();
            connection.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean delVenueInfo(String id) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        boolean isSuccess = false;
        try {

            connection = getConnection();
            String preQueryStatement = "DELETE FROM `venue` WHERE `venueId` = ?;";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, id);
            int rowCount = pStatement.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStatement.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(staffAccountDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    public boolean EditVenueInfo(String venueId, String description, String venueName, String venueStatus,
            String type, String location, String personInChargeId, String bookFee) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        boolean isSuccess = false;
        try {

            connection = getConnection();
            String preQueryStatement
                    = "UPDATE "
                    + "`venue` "
                    + "SET "
                    + "`Description`=?,"
                    + "`venueName`= ?,"
                    + "`venueStatus`= ? ,"
                    + "`Type`= ? ,"
                    + "`Location`= ? ,"
                    + "`Person_in_charge_id`= ? ,"
                    + "`Bookfee`= ? "
                    + " WHERE `venueId` =  ? ;";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, description);
            pStatement.setString(2, venueName);
            pStatement.setString(3, venueStatus);
            pStatement.setString(4, type);
            pStatement.setString(5, location);
            pStatement.setString(6, personInChargeId);
            pStatement.setString(7, bookFee);
            pStatement.setString(8, venueId);

            int rowCount = pStatement.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStatement.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(staffAccountDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    public ArrayList<venueBean1> getAllvenue() throws IOException {

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<venueBean1> venues = new ArrayList<>();

        try {
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM venue";
            pStatement = connection.prepareStatement(preQueryStatement);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                String venueId = rs.getString("venueId");
                String description = rs.getString("Description");
                String venueName = rs.getString("venueName");
                String venueStatus = rs.getString("venueStatus");
                String type = rs.getString("Type");
                String location = rs.getString("Location");
                String personInChargeId = rs.getString("Person_in_charge_id");
                String bookFee = rs.getString("Bookfee");
                venues.add(new venueBean1(venueId, description, venueName, venueStatus, type, location, personInChargeId, bookFee));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }

    public venueBean1 getVenueById(String id) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        venueBean1 venue = null;

        String venueId = "";
        String description = "";
        String venueName = "";
        String venueStatus = "";
        String type = "";
        String location = "";
        String personInChargeId = "";
        String bookFee = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM venue WHERE venueId = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, id);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {

                venueId = rs.getString("venueId");
                description = rs.getString("Description");
                venueName = rs.getString("venueName");
                venueStatus = rs.getString("venueStatus");
                type = rs.getString("Type");
                location = rs.getString("Location");
                personInChargeId = rs.getString("Person_in_charge_id");
                bookFee = rs.getString("Bookfee");

                venue = new venueBean1(venueId, description, venueName, venueStatus, type, location, personInChargeId, bookFee);

            } else {
                return null;
            }

            pStatement.close();
            connection.close();

        } catch (SQLException ex) {

            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return venue;
    }

}

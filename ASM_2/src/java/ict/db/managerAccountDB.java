/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sam
 */
public class managerAccountDB {

    private String dburl = "";
    private String dbuser = "";
    private String dbpassword = "";

    public managerAccountDB(String dburl, String dbuser, String dbpassword) {
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

    public boolean isValidUser(String email, String password) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        boolean isValid = false;
        try {
            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM manager WHERE email=? AND password=?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, email);
            pStatement.setString(2, password);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                isValid = true;
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
        return isValid;
    }

    public String getUsernameByEmail(String email) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        String name = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM manager WHERE email = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, email);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            } else {
                return "error";
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

        return name;
    }
    
    public String getManagerIdByEmail(String email) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        String name = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM manager WHERE email = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, email);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            } else {
                return "error";
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

        return name;
    }

}

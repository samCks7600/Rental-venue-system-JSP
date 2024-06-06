/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.Bean.staffBean;
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
public class staffAccountDB {

    private String dburl = "";
    private String dbuser = "";
    private String dbpassword = "";

    public staffAccountDB(String dburl, String dbuser, String dbpassword) {
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
                    = "SELECT * FROM staff WHERE email=? AND password=?";
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

    public boolean ableEmailSignUp(String email) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        boolean isValid = true;
        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM `staff` WHERE email = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, email);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                isValid = false;
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

    public boolean addStaffInfo(String email, String username, String pwd) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        boolean isSuccess = false;
        try {

            if (ableEmailSignUp(email) != true) {
                return false;
            }

            connection = getConnection();

            String preQueryStatement
                    = "INSERT INTO `staff` (`staffId`, `name`, `email`, `password`) VALUES (NULL,?,?,?);";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, username);
            pStatement.setString(2, email);
            pStatement.setString(3, pwd);

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

    public boolean delStaffInfo(String id) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        boolean isSuccess = false;
        try {

            connection = getConnection();
            String preQueryStatement= "DELETE FROM staff WHERE staffId = ?";
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

    public boolean EditStaffInfo(String id, String name, String email, String password) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        boolean isSuccess = false;
        try {

            connection = getConnection();
            String preQueryStatement= "UPDATE staff SET name = ?, email = ?,password = ? WHERE staffId = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, name);
            pStatement.setString(2, email);
            pStatement.setString(3, password);
            pStatement.setString(4, id);

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

    public String getUsernameByEmail(String email) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        String name = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM staff WHERE email = ?";
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
    
    public String getStaffIdByEmail(String email) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        String staffId = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM staff WHERE email = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, email);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                staffId = rs.getString("staffId");
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

        return staffId;
    }
    
    public staffBean getStaffById(String id) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        staffBean staff = null;
        
        String email = "";
        String username = "";
        String password = ""; 

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM staff WHERE staffId = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, id);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                
                
                email = rs.getString("email");
                password = rs.getString("password");
                username = rs.getString("name");
          
                staff = new staffBean(id , email , username , password);
                
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
        

         return staff;
    }

    public ArrayList<staffBean> getAllStaff() throws IOException {

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<staffBean> staffs = new ArrayList<>();

        try {
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM staff";
            pStatement = connection.prepareStatement(preQueryStatement);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("staffId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                staffs.add(new staffBean(id, email, name, password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }

}

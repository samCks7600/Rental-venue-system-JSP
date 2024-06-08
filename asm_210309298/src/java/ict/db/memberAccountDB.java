/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.memberBean;
import java.io.IOException;
import static java.lang.System.console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sam
 */
public class memberAccountDB {

    private String dburl = "";
    private String dbuser = "";
    private String dbpassword = "";

    public memberAccountDB(String dburl, String dbuser, String dbpassword) {
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
                    = "SELECT * FROM member WHERE email=? AND password=?";
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
                    = "SELECT * FROM member WHERE email = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, email);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                isValid = false;
            } else {
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

    public boolean addUserInfo(String email, String username, String pwd) {

        Connection connection = null;
        PreparedStatement pStatement = null;
        boolean isSuccess = false;

        boolean ableEmail;
        ableEmail = ableEmailSignUp(email);
        try {

            if (!ableEmail) {
                return false;
            }

            connection = getConnection();
            String preQueryStatement = "INSERT INTO `member`(`memberId`,`name`,`email`,`password`,`availableBooking`) VALUES (NULL , ? , ? , ? , 'true' );";
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

    public String getUsernameByEmail(String email) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        String name = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM member WHERE email = ?";
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

    public String getMemberIdByEmail(String email) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        String id = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM member WHERE email = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, email);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                id = rs.getString("memberId");
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

        return id;
    }

    public memberBean getMemberById(String memberId) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        String name = "";
        String email = "";
        String password = "";
        String availableBooking = "";
        memberBean member = null ;
        

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM member WHERE memberId = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, memberId);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");;
                email = rs.getString("email");;
                password = rs.getString("password");
                availableBooking = rs.getString("availableBooking");
                
                member = new memberBean(memberId,email,name,password,availableBooking);
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

        return member;
    }
    
    public ArrayList<memberBean> getAllMember() throws IOException {

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<memberBean> members = new ArrayList<>();

        try {
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM member";
            pStatement = connection.prepareStatement(preQueryStatement);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("memberId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String availableBooking = rs.getString("availableBooking");
                members.add(new memberBean(id, email, name, password,availableBooking));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

}
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
import java.util.ArrayList;

/**
 *
 * @author sam
 */
public class reportDB {

    private String dburl = "";
    private String dbuser = "";
    private String dbpassword = "";

    public reportDB(String dburl, String dbuser, String dbpassword) {
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

    public ArrayList<Double> bookingRate(String year, String venueId) throws IOException {

        ArrayList<Double> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;

        String sql = "SELECT\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 1 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Jan,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 2 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Feb,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 3 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Mar,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 4 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Apr,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 5 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS May,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 6 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Jun,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 7 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Jul,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 8 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Aug,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 9 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Sep,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 10 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Oct,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 11 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS Nov,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 12 THEN 1 ELSE 0 END) / DAY(LAST_DAY(DATE_FORMAT(bookingDate, '%Y-%m-01'))), 0) AS `Dec`\n"
                + "FROM booking\n"
                + "WHERE YEAR(bookingDate) = ? AND venueId = ?;";
        try {
            connection = getConnection();
            String preQueryStatement = sql;
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, year);
            pStatement.setString(2, venueId);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                results.add(Double.parseDouble(rs.getString("Jan")));
                results.add(Double.parseDouble(rs.getString("Feb")));
                results.add(Double.parseDouble(rs.getString("Mar")));
                results.add(Double.parseDouble(rs.getString("Apr")));
                results.add(Double.parseDouble(rs.getString("May")));
                results.add(Double.parseDouble(rs.getString("Jun")));
                results.add(Double.parseDouble(rs.getString("Jul")));
                results.add(Double.parseDouble(rs.getString("Aug")));
                results.add(Double.parseDouble(rs.getString("Sep")));
                results.add(Double.parseDouble(rs.getString("Oct")));
                results.add(Double.parseDouble(rs.getString("Nov")));
                results.add(Double.parseDouble(rs.getString("Dec")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;

    }

    public double YearBookingRate(String year, String venueId) throws IOException {

        double result = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;

        String sql = "SELECT COUNT(*) / 365 AS `bookingRate`\n"
                + "FROM `booking`\n"
                + "WHERE `venueId` = ?\n"
                + "  AND YEAR(`bookingDate`) = ?;";
        try {
            connection = getConnection();
            String preQueryStatement = sql;
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, venueId);
            pStatement.setString(2, year);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                result = Double.parseDouble(rs.getString("bookingRate"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public ArrayList<Double> AttendanceRate(String year, String memberId) throws IOException {

        ArrayList<Double> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;

        String sql = "SELECT\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 1 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 1 THEN 1 ELSE 0 END), 0)  AS Jan,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 2 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 2 THEN 1 ELSE 0 END), 0)  AS Feb,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 3 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 3 THEN 1 ELSE 0 END), 0)  AS Mar,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 4 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 4 THEN 1 ELSE 0 END), 0)  AS Apr,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 5 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 5 THEN 1 ELSE 0 END), 0)  AS May,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 6 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 6 THEN 1 ELSE 0 END), 0)  AS Jun,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 7 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 7 THEN 1 ELSE 0 END), 0)  AS Jul,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 8 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 8 THEN 1 ELSE 0 END), 0)  AS Aug,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 9 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) / SUM(CASE WHEN MONTH(bookingDate) = 9 THEN 1 ELSE 0 END), 0)  AS Sep,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 10 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) /SUM(CASE WHEN MONTH(bookingDate) = 10 THEN 1 ELSE 0 END), 0) AS Oct,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 11 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) /SUM(CASE WHEN MONTH(bookingDate) = 11 THEN 1 ELSE 0 END), 0) AS Nov,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 12 THEN IF(checkInOut IS NOT NULL, 1, 0) ELSE 0 END) /SUM(CASE WHEN MONTH(bookingDate) = 12 THEN 1 ELSE 0 END), 0) AS `Dec`\n"
                + "FROM booking\n"
                + "WHERE memberId = ? AND YEAR(bookingDate) = ?;";

        try {
            connection = getConnection();
            String preQueryStatement = sql;
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, memberId);
            pStatement.setString(2, year);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                results.add(Double.parseDouble(rs.getString("Jan")));
                results.add(Double.parseDouble(rs.getString("Feb")));
                results.add(Double.parseDouble(rs.getString("Mar")));
                results.add(Double.parseDouble(rs.getString("Apr")));
                results.add(Double.parseDouble(rs.getString("May")));
                results.add(Double.parseDouble(rs.getString("Jun")));
                results.add(Double.parseDouble(rs.getString("Jul")));
                results.add(Double.parseDouble(rs.getString("Aug")));
                results.add(Double.parseDouble(rs.getString("Sep")));
                results.add(Double.parseDouble(rs.getString("Oct")));
                results.add(Double.parseDouble(rs.getString("Nov")));
                results.add(Double.parseDouble(rs.getString("Dec")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;

    }

    public ArrayList<Double> AttendanceCounts(String year, String memberId) throws IOException {

        ArrayList<Double> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;

        String sql = "SELECT\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 1 THEN 1 ELSE 0 END), 0) AS Jan,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 2 THEN 1 ELSE 0 END), 0) AS Feb,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 3 THEN 1 ELSE 0 END), 0) AS Mar,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 4 THEN 1 ELSE 0 END), 0) AS Apr,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 5 THEN 1 ELSE 0 END), 0) AS May,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 6 THEN 1 ELSE 0 END), 0) AS Jun,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 7 THEN 1 ELSE 0 END), 0) AS Jul,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 8 THEN 1 ELSE 0 END), 0) AS Aug,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 9 THEN 1 ELSE 0 END), 0) AS Sep,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 10 THEN 1 ELSE 0 END), 0) AS Oct,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 11 THEN 1 ELSE 0 END), 0) AS Nov,\n"
                + "    COALESCE(SUM(CASE WHEN MONTH(bookingDate) = 12 THEN 1 ELSE 0 END), 0) AS 'Dec'\n"
                + "FROM booking\n"
                + "WHERE YEAR(bookingDate) = ? AND memberId = ?;";

        try {
            connection = getConnection();
            String preQueryStatement = sql;
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, year);
            pStatement.setString(2, memberId);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                results.add(Double.parseDouble(rs.getString("Jan")));
                results.add(Double.parseDouble(rs.getString("Feb")));
                results.add(Double.parseDouble(rs.getString("Mar")));
                results.add(Double.parseDouble(rs.getString("Apr")));
                results.add(Double.parseDouble(rs.getString("May")));
                results.add(Double.parseDouble(rs.getString("Jun")));
                results.add(Double.parseDouble(rs.getString("Jul")));
                results.add(Double.parseDouble(rs.getString("Aug")));
                results.add(Double.parseDouble(rs.getString("Sep")));
                results.add(Double.parseDouble(rs.getString("Oct")));
                results.add(Double.parseDouble(rs.getString("Nov")));
                results.add(Double.parseDouble(rs.getString("Dec")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;

    }

    public double YearAttendanceRate(String year, String memberId) throws IOException {

        double result = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;

        String sql = "SET @year = ?;\n"
                + "SET @memberId = ?;\n"
                + "\n"
                + "SELECT\n"
                + "    IFNULL(COUNT(DISTINCT CASE WHEN checkInOut IS NOT NULL THEN bookingId END) / COUNT(DISTINCT bookingId), 0) AS attendance_rate\n"
                + "FROM booking\n"
                + "WHERE memberId = @memberId AND YEAR(bookingDate) = @year;";

        try {
            connection = getConnection();
            String preQueryStatement = sql;
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, year);
            pStatement.setString(2, memberId);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                result = Double.parseDouble(rs.getString("attendance_rate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public int YearBookingCount(String year, String memberId) throws IOException {

        int result = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;

        String sql = "SELECT COUNT(*) as booking_count\n"
                + "FROM booking\n"
                + "WHERE memberId = ? AND YEAR(bookingDate) = ?;";

        try {
            connection = getConnection();
            String preQueryStatement = sql;
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, memberId);
            pStatement.setString(2, year);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                result = Integer.parseInt(rs.getString("booking_count"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public ArrayList<Double> IncomeResults(String year, String venueId) throws IOException {

        ArrayList<Double> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;

        String sql = "SELECT\n"
                + "    venueId,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 1 THEN fee ELSE 0 END), 0) AS Jan,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 2 THEN fee ELSE 0 END), 0) AS Feb,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 3 THEN fee ELSE 0 END), 0) AS Mar,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 4 THEN fee ELSE 0 END), 0) AS Apr,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 5 THEN fee ELSE 0 END), 0) AS May,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 6 THEN fee ELSE 0 END), 0) AS Jun,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 7 THEN fee ELSE 0 END), 0) AS Jul,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 8 THEN fee ELSE 0 END), 0) AS Aug,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 9 THEN fee ELSE 0 END), 0) AS Sep,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 10 THEN fee ELSE 0 END), 0) AS Oct,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 11 THEN fee ELSE 0 END), 0) AS Nov,\n"
                + "    IFNULL(SUM(CASE WHEN MONTH(bookingDate) = 12 THEN fee ELSE 0 END), 0) AS `Dec`\n"
                + "FROM booking\n"
                + "WHERE YEAR(bookingDate) = ?\n"
                + "AND `venueId` = ?\n"
                + "GROUP BY venueId;";

        try {
            connection = getConnection();
            String preQueryStatement = sql;
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, year);
            pStatement.setString(2, venueId);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {

                results.add(Double.parseDouble(rs.getString("Jan")));
                results.add(Double.parseDouble(rs.getString("Feb")));
                results.add(Double.parseDouble(rs.getString("Mar")));
                results.add(Double.parseDouble(rs.getString("Apr")));
                results.add(Double.parseDouble(rs.getString("May")));
                results.add(Double.parseDouble(rs.getString("Jun")));
                results.add(Double.parseDouble(rs.getString("Jul")));
                results.add(Double.parseDouble(rs.getString("Aug")));
                results.add(Double.parseDouble(rs.getString("Sep")));
                results.add(Double.parseDouble(rs.getString("Oct")));
                results.add(Double.parseDouble(rs.getString("Nov")));
                results.add(Double.parseDouble(rs.getString("Dec")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;

    }

    public double yearIncomeResults(String year, String venueId) throws IOException {

        double result  =0;
        Connection connection = null;
        PreparedStatement pStatement = null;

        String sql = "SELECT\n"
                + "    IFNULL(SUM(fee), 0) AS yearly_total\n"
                + "FROM booking\n"
                + "WHERE YEAR(bookingDate) = ?\n"
                + "AND venueId = ? \n"
                + "GROUP BY venueId;";

        try {
            connection = getConnection();
            String preQueryStatement = sql;
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, year);
            pStatement.setString(2, venueId);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                result = Double.parseDouble(rs.getString("yearly_total"));
    
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

}

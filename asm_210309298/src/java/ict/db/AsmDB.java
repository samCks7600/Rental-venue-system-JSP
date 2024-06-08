/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.BookingRecordBean;
import ict.bean.GuestBean;
import ict.bean.ReceiptBean;
import ict.bean.VenueBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

/**
 *
 * @author user
 */
public class AsmDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public AsmDB() {
        url = "jdbc:mysql://localhost:3306/epl_db";
        username = "root";
        password = "";
    }

    public AsmDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection(url, username, password);
    }

    public int addUser(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connection = getConnection();
            String SQL = "INSERT INTO member(username,password) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rows = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                result = (int) rs.getLong(1);
                System.out.println(String.format("Username: %s, id: %s", username, result));
            } else {
                throw new SQLException("Failed");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
        }
        return result;
    }

    public int addVenue(String name, String description, int status) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connection = getConnection();
            String SQL = "INSERT INTO venue(name,description,status) VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, status);
            int rows = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                result = (int) rs.getLong(1);
                System.out.println(String.format("Username: %s, id: %s", name, result));
            } else {
                throw new SQLException("Failed");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
        }
        return result;
    }

    //Show a list of all venue
    public ArrayList queryVenue() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM venue where venueStatus = \"able\" ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                VenueBean vb = new VenueBean();
                vb.setVenueId(rs.getInt(1));
                vb.setDescription(rs.getString(2));
                vb.setVenueName(rs.getString(3));
                vb.setVenueStatus(rs.getString(4));
                vb.setType(rs.getString(5));
                vb.setLocation(rs.getString(6));
                vb.setPerson_in_charge_id(rs.getInt(7));
                vb.setBookfee(rs.getInt(8));

                list.add(vb);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    public ArrayList queryRequest() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                BookingRecordBean vb = new BookingRecordBean();
                vb.setBookingid(rs.getInt(1));

                vb.setMemberid(rs.getInt(2));
                vb.setVenueid(rs.getInt(3));
                vb.setInvitationType(rs.getInt(4));
                vb.setStatus(rs.getString(5));
                vb.setFee(rs.getInt(6));
                vb.setInvoice(rs.getString(7));
                vb.setStaffid(rs.getInt(8));
                vb.setDesc(rs.getString(9));
                vb.setTimeType(rs.getString(10));
                vb.setBookingDate(rs.getString(11));

                list.add(vb);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    //query Venue By ID
    public VenueBean queryVenueByID(int id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        VenueBean vb = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM VENUE WHERE VENUEID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = null;
            pstmt.executeQuery();
            rs = pstmt.getResultSet();

            while (rs.next()) {
                vb = new VenueBean();

                vb.setVenueId(rs.getInt("venueId"));
                vb.setDescription(rs.getString("Description"));
                vb.setVenueName(rs.getString("venueName"));
                vb.setVenueStatus(rs.getString("venueStatus"));
                vb.setType(rs.getString("Type"));
                vb.setLocation(rs.getString("Location"));
                vb.setPerson_in_charge_id(rs.getInt("Person_in_charge_id"));
                vb.setBookfee(rs.getInt("Bookfee"));

            }
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return vb;

    }
    //show guest list

    public ArrayList queryGuest() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Guest";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                GuestBean gb = new GuestBean();
                gb.setId(rs.getInt(1));
                gb.setName(rs.getString(2));
                gb.setEmail(rs.getString(3));
                list.add(gb);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    public void addRequest(String user_id, String venueID, String date, String time) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String sql = "INSERT into booking (memberId, venueId, status, TimeType, bookingDate) values(?,?,?,?,?);";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, user_id);
            pStmnt.setString(2, venueID);
            pStmnt.setString(3, "request");
            pStmnt.setString(4, date);
            pStmnt.setString(5, time);

            pStmnt.executeUpdate();
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList queryRecord() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                BookingRecordBean cb = new BookingRecordBean();

                cb.setBookingid(rs.getInt("bookingId"));
                cb.setMemberid(rs.getInt("memberId"));
                cb.setVenueid(rs.getInt("venueId"));
                cb.setInvitationType(rs.getInt("invitationType"));
                cb.setStatus(rs.getString("status"));
                cb.setFee(rs.getInt("fee"));
                cb.setInvoice(rs.getString("invoice"));
                cb.setStaffid(rs.getInt("StaffId"));
                cb.setDesc(rs.getString("description"));
                cb.setTimeType(rs.getString("TimeType"));
                cb.setBookingDate(rs.getString("bookingDate"));

                list.add(cb);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    public BookingRecordBean queryRecordByID(int id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        BookingRecordBean cb = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM booking WHERE bookingId=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = null;
            pstmt.executeQuery();
            rs = pstmt.getResultSet();

            while (rs.next()) {
                cb = new BookingRecordBean();

                cb.setBookingid(rs.getInt("bookingId"));
                cb.setMemberid(rs.getInt("memberId"));
                cb.setVenueid(rs.getInt("venueId"));
                cb.setInvitationType(rs.getInt("invitationType"));
                cb.setStatus(rs.getString("status"));
                cb.setFee(rs.getInt("fee"));
                cb.setInvoice(rs.getString("invoice"));
                cb.setStaffid(rs.getInt("StaffId"));
                cb.setDesc(rs.getString("description"));
                cb.setTimeType(rs.getString("TimeType"));
                cb.setBookingDate(rs.getString("bookingDate"));

            }
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return cb;

    }

    public void editRecord(BookingRecordBean cb) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();

            String SQL = "Update booking set bookingDate = ?,  TimeType= ? where bookingid =?";
            pstmt = con.prepareStatement(SQL);

//            pstmt.setString(1, cb.getBookingDate());
//            pstmt.setString(2, cb.getTimeType());
            pstmt.setString(1, cb.getBookingDate());
            pstmt.setString(2, cb.getTimeType());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isRequestValid(String selectedDate, String selectedTime, String selectedVenue) {
        boolean requestValid = true;
        String sql = "SELECT * FROM booking WHERE status = \"approve\" AND bookingDate = ? AND TimeType = ? AND venueId = ?";

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ResultSet rs = null;
        try {
            cnnct = getConnection(); // getConnection()方法需要自己定義
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, selectedDate);
            pStmnt.setString(2, selectedTime);
            pStmnt.setString(3, selectedVenue);
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                requestValid = false;
            }
            rs.close();
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return requestValid;
    }
//    check incharge staff
//return venueid

    public ArrayList<Integer> queryVenueIdsByStaffId(String staffId) {
        ArrayList<Integer> venueIds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT venueId FROM venue WHERE Person_in_charge_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, staffId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int venueId = rs.getInt("venueId");
                venueIds.add(venueId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return venueIds;
    }

    public ArrayList<BookingRecordBean> queryRecordsByVenueIds(int venueId) {
        ArrayList<BookingRecordBean> records = new ArrayList<BookingRecordBean>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM booking WHERE venueid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, venueId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookingRecordBean record = new BookingRecordBean();
                record.setBookingid(rs.getInt("bookingId"));
                record.setVenueid(rs.getInt("venueId"));
                record.setMemberid(rs.getInt("memberId"));
                record.setStatus(rs.getString("status"));
                record.setFee(rs.getInt("fee"));
                record.setTimeType(rs.getString("TimeType"));
                record.setBookingDate(rs.getString("bookingDate"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return records;
    }

    public void updateBookingStatus(int bookingId, String newStatus, double newFee, int newStaffId) {
        try {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            conn = getConnection();
            //sql
            String sql = "UPDATE booking SET status=?, fee=?, staffId=? WHERE bookingId=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newStatus);
            pstmt.setDouble(2, newFee);
            pstmt.setInt(3, newStaffId);
            pstmt.setInt(4, bookingId);

            // execute
            pstmt.executeUpdate();

            // close
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<BookingRecordBean> getBookingListByUserId(String memberId) {
        List<BookingRecordBean> bookingList = new ArrayList<>();

        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            // 準備SQL語句，獲取指定用戶的booking資訊以及場地訊息
            String sql = "SELECT * FROM booking b JOIN venue v ON b.venueid = v.venueid WHERE b.memberid = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();

            // 循環處理ResultSet，將資料添加到List中
            while (rs.next()) {
                BookingRecordBean booking = new BookingRecordBean();

                booking.setBookingid(rs.getInt("bookingId"));
                booking.setVenueid(rs.getInt("venueId"));
                booking.setMemberid(rs.getInt("memberId"));
                booking.setStatus(rs.getString("status"));
                booking.setFee(rs.getInt("fee"));
                booking.setTimeType(rs.getString("TimeType"));
                booking.setBookingDate(rs.getString("bookingDate"));

                VenueBean venue = new VenueBean();
                venue.setVenueId(rs.getInt("venueId"));
                venue.setVenueName(rs.getString("venueName"));
                venue.setType(rs.getString("Type"));
                venue.setLocation(rs.getString("location"));
                venue.setDescription(rs.getString("description"));

                booking.setVenue(venue);
                bookingList.add(booking);
            }

            // 關閉ResultSet、PreparedStatement和資料庫連接
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bookingList;
    }

    public void saveGuests(int bookingId, String[] guestNames, String[] guestEmails) {
        try {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            conn = getConnection();
            String sql = "INSERT INTO booking_guest (bookingId,guestName, email) VALUES (?,?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < guestNames.length; i++) {
                pstmt.setInt(1, bookingId);
                pstmt.setString(2, guestNames[i]);
                pstmt.setString(3, guestEmails[i]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateBookingType(int bookingId, int newType) {

        try {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            conn = getConnection();
            // 準備SQL語句
            String sql = "UPDATE booking SET invitationType = ? WHERE bookingid = ?";
            pstmt = conn.prepareStatement(sql);
            // 設置參數
            pstmt.setInt(1, newType);
            pstmt.setInt(2, bookingId);
            // 執行更新操作
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void savePaymentProofToDB(int bookingId) {
        try {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            conn = getConnection();
            // 准备SQL语句
            //String sql = "INSERT INTO receipt (booking_id, upload_date, filename, filedata) VALUES (?, ?, ?, ?)";
            String sql = "INSERT INTO receipt (bookingid, uploadedAt, filename) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            // 将booking ID、上传日期、文件名和文件数据设置为SQL语句的参数
            pstmt.setInt(1, bookingId);
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(3, Integer.toString(bookingId) + "_receipt");

            // 执行SQL语句
            pstmt.executeUpdate();

            // 关闭数据库连接
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePaymentStatus(int bookingId, String newStatus) {
        try {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            conn = getConnection();
            //sql
            String sql = "UPDATE booking SET status=? WHERE bookingId=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newStatus);

            pstmt.setInt(2, bookingId);

            // execute
            pstmt.executeUpdate();

            // close
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<BookingRecordBean> showPaidBookingsByEmployee(String staffId) {
        List<BookingRecordBean> bookingList = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM booking b JOIN venue v ON b.venueid = v.venueid WHERE b.staffId = ? AND b.status = 'paid'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookingRecordBean booking = new BookingRecordBean();

                booking.setBookingid(rs.getInt("bookingId"));
                booking.setVenueid(rs.getInt("venueId"));
                booking.setMemberid(rs.getInt("memberId"));
                booking.setStatus(rs.getString("status"));
                booking.setFee(rs.getInt("fee"));
                booking.setTimeType(rs.getString("TimeType"));
                booking.setBookingDate(rs.getString("bookingDate"));

                VenueBean venue = new VenueBean();
                venue.setVenueId(rs.getInt("venueId"));
                venue.setVenueName(rs.getString("venueName"));
                venue.setType(rs.getString("Type"));
                venue.setLocation(rs.getString("location"));
                venue.setDescription(rs.getString("description"));

                booking.setVenue(venue);
                bookingList.add(booking);
            }

            // 關閉ResultSet、PreparedStatement和資料庫連接
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bookingList;
    }

    public List<GuestBean> getAllGuestsForBooking(int bookingId) {
        List<GuestBean> guestList = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM booking_guest WHERE bookingid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                GuestBean guest = new GuestBean();
                guest.setName(rs.getString("guestName"));
                guest.setEmail(rs.getString("email"));
                guestList.add(guest);
            }

            // 關閉ResultSet、PreparedStatement和資料庫連接
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return guestList;
    }

    public BookingRecordBean getBookingById(int bookingId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BookingRecordBean booking = null;

        try {
            Connection conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM booking WHERE bookingId = ?");
            stmt.setInt(1, bookingId);
            rs = stmt.executeQuery();

            booking = new BookingRecordBean();
            booking.setBookingid(rs.getInt("bookingId"));
            booking.setVenueid(rs.getInt("venueId"));
            booking.setInvitationType(rs.getInt("invitationType"));
            booking.setStatus(rs.getString("status"));
            booking.setFee(rs.getInt("fee"));
            booking.setStaffid(rs.getInt("staffid"));
            booking.setTimeType(rs.getString("TimeType"));
            booking.setBookingDate(rs.getString("BookingDate"));

        } catch (SQLException e) {
            // 處理異常
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return booking;
    }

    public ReceiptBean getReceiptByBookingId(int bookingId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ReceiptBean receipt = null;

        try {
            Connection conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM receipt WHERE bookingId = ?");
            stmt.setInt(1, bookingId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                receipt = new ReceiptBean();
                receipt.setReceiptid(rs.getInt("receiptId"));
                receipt.setBookingid(rs.getInt("bookingId"));
                receipt.setFilename(rs.getString("filename"));
                receipt.setUploadedAt(rs.getTimestamp("uploadedAt").toLocalDateTime());
            }

        } catch (SQLException e) {
            // 处理异常
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return receipt;
    }

    public List<BookingRecordBean> getBookingListByStaffId(String staffId) {
        List<BookingRecordBean> bookingList = new ArrayList<>();

        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            // 準備SQL語句，獲取指定用戶的booking資訊以及場地訊息
            String sql = "SELECT * FROM booking b JOIN venue v ON b.venueid = v.venueid WHERE b.staffid = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffId);
            rs = pstmt.executeQuery();

            // 循環處理ResultSet，將資料添加到List中
            while (rs.next()) {
                BookingRecordBean booking = new BookingRecordBean();

                booking.setBookingid(rs.getInt("bookingId"));
                booking.setVenueid(rs.getInt("venueId"));
                booking.setMemberid(rs.getInt("memberId"));
                booking.setStatus(rs.getString("status"));
                booking.setFee(rs.getInt("fee"));
                booking.setTimeType(rs.getString("TimeType"));
                booking.setBookingDate(rs.getString("bookingDate"));
                booking.setCheckInTime(rs.getString("checkInTime"));
                booking.setCheckOutTime(rs.getString("checkOutTime"));

                VenueBean venue = new VenueBean();
                venue.setVenueId(rs.getInt("venueId"));
                venue.setVenueName(rs.getString("venueName"));
                venue.setType(rs.getString("Type"));
                venue.setLocation(rs.getString("location"));
                venue.setDescription(rs.getString("description"));

                booking.setVenue(venue);
                bookingList.add(booking);
            }

            // 關閉ResultSet、PreparedStatement和資料庫連接
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bookingList;
    }

    public boolean updateCheckIn(int bookingId, String checkInTime) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("UPDATE booking SET checkInTime = ? WHERE bookingId = ?");
            stmt.setString(1, checkInTime);
            stmt.setInt(2, bookingId);
            int rows = stmt.executeUpdate();
            return rows > 0; // 如果更新成功，回傳 true
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false; // 如果更新失敗或發生異常，回傳 false
    }
    public boolean updateCheckOut(int bookingId, String checkOutTime) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("UPDATE booking SET checkOutTime = ? WHERE bookingId = ?");
            stmt.setString(1, checkOutTime);
            stmt.setInt(2, bookingId);
            int rows = stmt.executeUpdate();
            return rows > 0; // 如果更新成功，回傳 true
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false; // 如果更新失敗或發生異常，回傳 false
    }
    public boolean updateDescOut(int bookingId, String Desc) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("UPDATE booking SET description = ? WHERE bookingId = ?");
            stmt.setString(1, Desc);
            stmt.setInt(2, bookingId);
            int rows = stmt.executeUpdate();
            return rows > 0; // 如果更新成功，回傳 true
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false; // 如果更新失敗或發生異常，回傳 false
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.db.AsmDB;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class TestAddRecord {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/epl_db";
        String username = "root";
        String password = "";

        AsmDB AsmDB = new AsmDB(url, username, password);
        //AsmDB.addRequest("1001", "1","1","2023-04-06");
        //boolean isValid = AsmDB.isRequestValid("2023-04-06", "1", "1");

        //ArrayList<Integer> venueIds = AsmDB.queryVenueIdsByStaffId("123");
        //pass
        //System.out.println("Alist staff(2001)" + venueIds);
        //UPDATE booking SET status = 'waitingPayment' WHERE bookingid = 289;
        //AsmDB.updateBookingStatus(337,"waitingPayment");
        //System.out.println(AsmDB.getBookingListByUserId("1"));
//        String[] guestNames = {"John Smith", "Jane Doe", "Bob Johnson"};
//        String[] guestEmails = {"john.smith@example.com", "jane.doe@example.com", "bob.johnson@example.com"};
//        AsmDB.saveGuests(1, guestNames, guestEmails);
        
        //AsmDB.updateBookingType(331, 1);
        
        //AsmDB.updatePaymentStatus(292, "paid");
        //AsmDB.showPaidBookingsByEmployee("2001");
        //System.out.println(AsmDB.getBookingById(289));
        AsmDB.addRequest("1", "7","1","2023-04-04");
    }

}

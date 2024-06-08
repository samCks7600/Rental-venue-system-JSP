package ict.test;

import ict.db.AsmDB;



public class TestCreateDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/epl_db";
        String username = "root";
        String password = "";

        AsmDB AsmDB = new AsmDB(url, username, password);


//        BookDB.addRecord("1", "John", "1234" , 15);
//        BookDB.addRecord("2", "Peter", "1236" , 17);
    }
}

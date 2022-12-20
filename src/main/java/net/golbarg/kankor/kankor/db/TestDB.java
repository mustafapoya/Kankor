package net.golbarg.kankor.kankor.db;

import java.sql.ResultSet;

public class TestDB {
    public static void main(String[] args) {

        try {
            ResultSet resultSet = DBController.executeQuery("select * from users;");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("id"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}

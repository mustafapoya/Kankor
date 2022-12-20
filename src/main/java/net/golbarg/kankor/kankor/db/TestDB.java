package net.golbarg.kankor.kankor.db;

import java.sql.ResultSet;

public class TestDB {
    public static void main(String[] args) {

        try {
            new TableUser().getAll();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}

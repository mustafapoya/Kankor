package net.golbarg.kankor.db;

public class TestDB {
    public static void main(String[] args) {

        try {
            new TableUser().getAll();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}

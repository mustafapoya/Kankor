package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Location;

import java.util.ArrayList;

public class TestDB {
    public static void main(String[] args) {

        try {
//            new TableUser().getAll();

            System.out.println(new TableLocation().findById(1));

            ArrayList<Location> list = new TableLocation().getAllProvince();
            for (Location location: list) {
                System.out.println(location);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}

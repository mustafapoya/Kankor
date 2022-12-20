package net.golbarg.kankor.kankor.db;

import net.golbarg.kankor.kankor.model.Gender;
import net.golbarg.kankor.kankor.model.Location;
import net.golbarg.kankor.kankor.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableUser implements CRUDHandler<User> {
    public static final String TABLE_NAME = "users";
    public static final String [] COLUMNS = {"ID", "NAME", "LAST_NAME", "FATHER_NAME", "USER_NAME", "PASSWORD",
                                             "LOCATION_ID", "SCHOOL_NAME", "PHONE_NUMBER", "GENDER", "PHOTO"};
    public static final String COLUMNS_STR = "ID, NAME, LAST_NAME, FATHER_NAME, USER_NAME, PASSWORD, LOCATION_ID, SCHOOL_NAME, PHONE_NUMBER, GENDER, PHOTO";

    public ArrayList<User> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<User> userList = new ArrayList<>();
        try {
            Connection connection = DBController.getLocalConnection();
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                User user = mapColumn(result);
                userList.add(user);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return userList;
    }

    public User findById(int id) {
        return null;
    }

    public boolean create(User user) {
        return false;
    }

    public boolean update(User user) {
        return false;
    }

    public boolean delete(User user) {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean emptyTable() {
        return false;
    }

    @Override
    public User mapColumn(ResultSet result) throws SQLException {
        return new User(
                result.getInt("ID"),
                result.getString("NAME"),
                result.getString("LAST_NAME"),
                result.getString("FATHER_NAME"),
                result.getString("USER_NAME"),
                result.getString("PASSWORD"),
                new Location(result.getInt("LOCATION_ID"), 0, "", 0, "", ""),
                result.getString("SCHOOL_NAME"),
                result.getString("PHONE_NUMBER"),
                Gender.getGender(result.getString("GENDER")),
                result.getString("PHOTO")
        );
    }
}

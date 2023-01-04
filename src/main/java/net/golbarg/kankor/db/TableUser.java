package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String query = "insert into users (NAME, LAST_NAME, FATHER_NAME, USER_NAME, PASSWORD, LOCATION_ID, SCHOOL_NAME, PHONE_NUMBER, GENDER, PHOTO) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, user);
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

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
    public PreparedStatement putValues(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getLastName());
        statement.setString(3, object.getFatherName());
        statement.setString(4, object.getUserName());
        statement.setString(5, object.getPassword());
        statement.setInt(6, object.getLocation().getId());
        statement.setString(7, object.getSchoolName());
        statement.setString(8, object.getPhoneNumber());
        statement.setString(9, object.getGender().getKey());
        statement.setString(10, object.getPhoto());
        return statement;
    }
}

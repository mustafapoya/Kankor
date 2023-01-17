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

    public boolean create(User object) {
        String query = String.format("insert into %s NAME, LAST_NAME, FATHER_NAME, USER_NAME, PASSWORD, LOCATION_ID, SCHOOL_NAME, PHONE_NUMBER, GENDER, PHOTO) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public User findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        User object = null;

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                object = mapColumn(resultSet);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return object;
    }

    public ArrayList<User> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<User> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                User object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    public boolean update(User object) {
        String query = "update " + TABLE_NAME + " set NAME = ?, LAST_NAME = ?, FATHER_NAME = ?, USER_NAME = ?, PASSWORD = ?, LOCATION_ID = ?, SCHOOL_NAME = ?, PHONE_NUMBER = ?, GENDER = ?, PHOTO = ? where id = ?";

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(11, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public boolean delete(User object) {
        String query = "DELETE from " + TABLE_NAME + " where id = ?";

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public int getCount() {
        String query = String.format("SELECT count(*) as record_count FROM %s", COLUMNS_STR, TABLE_NAME);

        int count = 0;

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                count = resultSet.getInt("record_count");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return count;
    }

    @Override
    public boolean emptyTable() {
        String query = "truncate table " + TABLE_NAME;

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

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

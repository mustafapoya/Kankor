package net.golbarg.kankor.db;

import net.golbarg.kankor.model.User;
import net.golbarg.kankor.model.UserEmail;

import java.sql.*;
import java.util.ArrayList;

public class TableUserEmail implements CRUDHandler<UserEmail> {
    public static final String TABLE_NAME = "USER_EMAILS";
    public static final String [] COLUMNS = {"ID", "USER_ID", "EMAIL", "PHONE", "TITLE", "CONTENT"};
    public static final String COLUMNS_STR = "ID, USER_ID, EMAIL, PHONE, TITLE, CONTENT";

    @Override
    public boolean create(UserEmail object) {
        String query = String.format("insert into %s (USER_ID, EMAIL, PHONE, TITLE, CONTENT) values (?, ?, ?, ?, ?)", TABLE_NAME);

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

    @Override
    public UserEmail findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        UserEmail object = null;

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

    @Override
    public ArrayList<UserEmail> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<UserEmail> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                UserEmail object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(UserEmail object) {
        String query = String.format("update %s set USER_ID = ?, EMAIL = ?, PHONE = ?, TITLE = ?, CONTENT = ? where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(6, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(UserEmail object) {
        String query = String.format("DELETE from %s where id = ?", TABLE_NAME);

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
    public UserEmail mapColumn(ResultSet result) throws SQLException {
        return new UserEmail(
                result.getInt("ID"),
                new User(result.getInt("USER_ID")),
                result.getString("EMAIL"),
                result.getString("PHONE"),
                result.getString("TITLE"),
                result.getString("CONTENT")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, UserEmail object) throws SQLException {
        statement.setInt(1, object.getUser().getId());
        statement.setString(2, object.getEmail());
        statement.setString(3, object.getPhone());
        statement.setString(4, object.getTitle());
        statement.setString(5, object.getContent());
        return statement;
    }
}

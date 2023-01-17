package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Config;
import net.golbarg.kankor.model.Email;

import java.sql.*;
import java.util.ArrayList;

public class TableEmail implements CRUDHandler<Email> {
    public static final String TABLE_NAME = "EMAILS";
    public static final String [] COLUMNS = {"ID", "EMAIL", "PHONE", "TITLE", "CONTENT"};
    public static final String COLUMNS_STR = "ID, EMAIL, PHONE, TITLE, CONTENT";


    @Override
    public boolean create(Email object) {
        String query = String.format("insert into %s (EMAIL, PHONE, TITLE, CONTENT) values (?, ?, ?, ?)", TABLE_NAME);

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
    public Email findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Email object = null;

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
    public ArrayList<Email> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Email> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Email object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Email object) {
        String query = String.format("update %s set EMAIL = ?, PHONE = ?, TITLE = ?, CONTENT = ? where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(5, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Email object) {
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
    public Email mapColumn(ResultSet result) throws SQLException {
        return new Email(
                result.getInt("ID"),
                result.getString("EMAIL"),
                result.getString("PHONE"),
                result.getString("TITLE"),
                result.getString("CONTENT")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Email object) throws SQLException {
        statement.setString(1, object.getEmail());
        statement.setString(2, object.getPhone());
        statement.setString(3, object.getTitle());
        statement.setString(4, object.getContent());
        return statement;
    }
}

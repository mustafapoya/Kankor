package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Config;
import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.User;

import java.sql.*;
import java.util.ArrayList;

public class TableConfig implements CRUDHandler<Config> {
    public static final String TABLE_NAME = "CONFIGS";
    public static final String [] COLUMNS = {"ID", "CONF_KEY", "CONF_VALUE", "CREATED_AT", "UPDATED_AT"};
    public static final String COLUMNS_STR = "ID, CONF_KEY, CONF_VALUE, CREATED_AT, UPDATED_AT";

    @Override
    public boolean create(Config object) {
        String query = String.format("insert into %s (CONF_KEY, CONF_VALUE, CREATED_AT, UPDATED_AT) values (?, ?, ?, ?)", TABLE_NAME);

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
    public Config findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Config object = null;

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
    public ArrayList<Config> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Config> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Config object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Config object) {
        String query = String.format("update %s set CONF_KEY = ?, CONF_VALUE = ?, CREATED_AT = ?, UPDATED_AT = ? where id = ?", TABLE_NAME);

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
    public boolean delete(Config object) {
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
    public Config mapColumn(ResultSet result) throws SQLException {
        return new Config(
                result.getInt("ID"),
                result.getString("CONF_KEY"),
                result.getString("CONF_VALUE"),
                result.getTimestamp("CREATED_AT").toLocalDateTime(),
                result.getTimestamp("UPDATED_AT").toLocalDateTime()
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Config object) throws SQLException {
        statement.setString(1, object.getKey());
        statement.setString(2, object.getValue());
        statement.setTimestamp(3, Timestamp.valueOf(object.getCreatedAt()));
        statement.setTimestamp(4, Timestamp.valueOf(object.getUpdatedAt()));
        return statement;
    }
}

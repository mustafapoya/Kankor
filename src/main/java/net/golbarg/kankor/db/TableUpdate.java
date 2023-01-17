package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Review;
import net.golbarg.kankor.model.Update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableUpdate implements CRUDHandler<Update>{
    public static final String TABLE_NAME = "UPDATES";
    public static final String [] COLUMNS = {"ID", "CONTENT", "DESCRIPTION"};
    public static final String COLUMNS_STR = "ID, CONTENT, DESCRIPTION";


    @Override
    public boolean create(Update object) {
        String query = String.format("insert into %s (CONTENT, DESCRIPTION) values (?, ?)", TABLE_NAME);

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
    public Update findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Update object = null;

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
    public ArrayList<Update> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Update> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Update object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Update object) {
        String query = String.format("update %s set CONTENT = ?, DESCRIPTION = ? where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(3, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Update object) {
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
    public Update mapColumn(ResultSet result) throws SQLException {
        return new Update(
                result.getInt("ID"),
                result.getString("CONTENT"),
                result.getString("DESCRIPTION")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Update object) throws SQLException {
        statement.setString(1, object.getContent());
        statement.setString(2, object.getDescription());
        return statement;
    }
}

package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.Resource;
import net.golbarg.kankor.model.ResourceCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableResource implements CRUDHandler<Resource> {
    public static final String TABLE_NAME = "RESOURCES";
    public static final String [] COLUMNS = {"ID", "CATEGORY_ID", "NAME", "FILE_NAME", "DESCRIPTION", "SIZE"};
    public static final String COLUMNS_STR = "ID, CATEGORY_ID, NAME, FILE_NAME, DESCRIPTION, `SIZE`";


    @Override
    public boolean create(Resource object) {
        String query = String.format("insert into %s (CATEGORY_ID, NAME, FILE_NAME, DESCRIPTION, `SIZE`) values (?, ?, ?, ?, ?)", TABLE_NAME);

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
    public Resource findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Resource object = null;

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
    public ArrayList<Resource> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Resource> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                resultList.add(mapColumn(result));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Resource object) {
        String query = String.format("update %s set CATEGORY_ID = ?, NAME = ?, FILE_NAME = ?, DESCRIPTION = ?, `SIZE` = ? where id = ?", TABLE_NAME);

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
    public boolean delete(Resource object) {
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
    public Resource mapColumn(ResultSet result) throws SQLException {
        return new Resource(
                result.getInt("ID"),
                new ResourceCategory(result.getInt("CATEGORY_ID"), ""),
                result.getString("NAME"),
                result.getString("FILE_NAME"),
                result.getString("DESCRIPTION"),
                result.getDouble("SIZE")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Resource object) throws SQLException {
        statement.setInt(1, object.getCategory().getId());
        statement.setString(2, object.getName());
        statement.setString(3, object.getFileName());
        statement.setString(4, object.getDescription());
        statement.setDouble(5, object.getSize());
        return statement;
    }
}

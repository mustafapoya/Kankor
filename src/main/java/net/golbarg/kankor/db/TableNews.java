package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.News;

import java.sql.*;
import java.util.ArrayList;

public class TableNews implements CRUDHandler<News> {
    public static final String TABLE_NAME = "NEWS";
    public static final String COLUMNS_STR = "ID, TITLE, CONTENT, DATE, URL_LINK, DESCRIPTION";

    @Override
    public boolean create(News object) {
        String query = "insert into locations (TITLE, CONTENT, `DATE`, URL_LINK, DESCRIPTION) values (?, ?, ?, ?, ?)";

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
    public News findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        News object = null;

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
    public ArrayList<News> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<News> resultList = new ArrayList<>();
        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                News object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(News object) {
        String query = "update news set TITLE = ?, CONTENT = ?, `DATE` = ?, URL_LINK = ?, DESCRIPTION = ? where id = ?";

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
    public boolean delete(News object) {
        String query = "DELETE from news where id = ?";

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
        String query = String.format("SELECT count(*) as record_count FROM %s", TABLE_NAME);

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
    public News mapColumn(ResultSet result) throws SQLException {
        return new News(
                result.getInt("ID"),
                result.getString("TITLE"),
                result.getString("CONTENT"),
                result.getDate("DATE"),
                result.getString("URL_LINK"),
                result.getString("DESCRIPTION")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, News object) throws SQLException {
        statement.setString(1, object.getTitle());
        statement.setString(2, object.getContent());
        statement.setDate(3, Date.valueOf(object.getDate().toString()));
        statement.setString(4, object.getUrl());
        statement.setString(5, object.getDescription());
        return statement;
    }
}

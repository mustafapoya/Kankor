package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.News;

import java.sql.*;
import java.util.ArrayList;

public class TableNews implements CRUDHandler<News> {
    public static final String TABLE_NAME = "NEWS";
    public static final String COLUMNS_STR = "ID, CATEGORY, TITLE, DESCRIPTION, URL_LINK, CONTENT, NEWS_DATE";

    @Override
    public boolean create(News object) {
        String query = String.format("insert into %s (CATEGORY, TITLE, DESCRIPTION, URL_LINK, CONTENT, NEWS_DATE) values (?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
        String query = String.format("update %s set CATEGORY = ?, TITLE = ?, DESCRIPTION = ?, URL_LINK = ?, CONTENT = ?, NEWS_DATE = ? where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(7, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(News object) {
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
                result.getString("CATEGORY"),
                result.getString("TITLE"),
                result.getString("DESCRIPTION"),
                result.getString("URL_LINK"),
                result.getString("CONTENT"),
                result.getDate("NEWS_DATE").toLocalDate()
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, News object) throws SQLException {
        statement.setString(1, object.getCategory());
        statement.setString(2, object.getTitle());
        statement.setString(3, object.getDescription());
        statement.setString(4, object.getUrlLink());
        statement.setString(5, object.getContent());
        statement.setDate(6, Date.valueOf(object.getNewsDate().toString()));
        return statement;
    }
}

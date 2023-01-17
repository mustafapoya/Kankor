package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Faculty;
import net.golbarg.kankor.model.NewsArticle;

import java.sql.*;
import java.util.ArrayList;

public class TableNewsArticle implements CRUDHandler<NewsArticle> {
    public static final String TABLE_NAME = "EMAILS";
    public static final String [] COLUMNS = {"ID", "TITLE", "CONTENT", "URL_LINK", "DATE", "DESCRIPTION", "CATEGORY"};
    public static final String COLUMNS_STR = "ID, TITLE, CONTENT, URL_LINK, DATE, DESCRIPTION, CATEGORY";

    @Override
    public boolean create(NewsArticle object) {
        String query = String.format("insert into %s (TITLE, CONTENT, URL_LINK, DATE, DESCRIPTION, CATEGORY) values (?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
    public NewsArticle findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        NewsArticle object = null;

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
    public ArrayList<NewsArticle> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<NewsArticle> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                NewsArticle object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(NewsArticle object) {
        String query = String.format("update %s set TITLE = ?, CONTENT = ?, URL_LINK = ?, DATE = ?, DESCRIPTION = ?, CATEGORY = ? where id = ?", TABLE_NAME);

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
    public boolean delete(NewsArticle object) {
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
    public NewsArticle mapColumn(ResultSet result) throws SQLException {

        return new NewsArticle(
                result.getInt("ID"),
                result.getString("TITLE"),
                result.getString("CONTENT"),
                result.getString("URL_LINK"),
                result.getDate("DATE"),
                result.getString("DESCRIPTION"),
                result.getInt("CATEGORY")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, NewsArticle object) throws SQLException {
        statement.setString(1, object.getTitle());
        statement.setString(2, object.getContent());
        statement.setString(3, object.getUrlLink());
        statement.setDate(4, Date.valueOf(object.getDate().toString()));
        statement.setString(5, object.getDescription());
        statement.setInt(6, object.getCategory());

        return statement;
    }

}

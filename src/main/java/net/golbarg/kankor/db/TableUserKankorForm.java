package net.golbarg.kankor.db;

import net.golbarg.kankor.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableUserKankorForm implements CRUDHandler<UserKankorForm> {
    public static final String TABLE_NAME = "USER_KANKOR_FORMS";
    public static final String COLUMNS_STR = "ID, USER_ID, FATHER_NAME, GRAND_FATHER_NAME, CURRENT_PROVINCE, CURRENT_DISTRICT, CURRENT_VILLAGE, ORIGIN_PROVINCE, ORIGIN_DISTRICT, ORIGIN_VILLAGE, GRADUATE_YEAR, TAZKIRA_ID, LANGUAGE";

    @Override
    public boolean create(UserKankorForm object) {
        String query = String.format("insert into %s (USER_ID, FATHER_NAME, GRAND_FATHER_NAME, CURRENT_PROVINCE, CURRENT_DISTRICT, CURRENT_VILLAGE, ORIGIN_PROVINCE, ORIGIN_DISTRICT, ORIGIN_VILLAGE, GRADUATE_YEAR, TAZKIRA_ID, LANGUAGE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
    public UserKankorForm findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        UserKankorForm object = null;

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
    public ArrayList<UserKankorForm> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<UserKankorForm> locationList = new ArrayList<>();
        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                UserKankorForm location = mapColumn(result);
                locationList.add(location);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return locationList;
    }

    @Override
    public boolean update(UserKankorForm object) {
        String query = String.format("update %s set USER_ID = ?, FATHER_NAME = ?, GRAND_FATHER_NAME = ?, CURRENT_PROVINCE = ?, CURRENT_DISTRICT = ?, CURRENT_VILLAGE = ?, ORIGIN_PROVINCE = ?, ORIGIN_DISTRICT = ?, ORIGIN_VILLAGE = ?, GRADUATE_YEAR = ?, TAZKIRA_ID = ?, LANGUAGE = ? where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(13, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(UserKankorForm object) {
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
    public UserKankorForm mapColumn(ResultSet result) throws SQLException {
        return new UserKankorForm(
                result.getInt("ID"),
                new User(result.getInt("USER_ID")),
                result.getString("FATHER_NAME"),
                result.getString("GRAND_FATHER_NAME"),
                new Location(result.getInt("CURRENT_PROVINCE"), 0, "", 0, "", ""),
                result.getString("CURRENT_DISTRICT"),
                result.getString("CURRENT_VILLAGE"),
                new Location(result.getInt("ORIGIN_PROVINCE"), 0, "", 0, "", ""),
                result.getString("ORIGIN_DISTRICT"),
                result.getString("ORIGIN_VILLAGE"),
                result.getInt("GRADUATE_YEAR"),
                result.getString("TAZKIRA_ID"),
                Language.getLanguage(result.getString("LANGUAGE"))
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, UserKankorForm object) throws SQLException {
        statement.setInt(1, object.getUser().getId());
        statement.setString(2, object.getFatherName());
        statement.setString(3, object.getGrandFatherName());
        statement.setInt(4, object.getCurrentLocation().getId());
        statement.setString(5, object.getCurrentDistrict());
        statement.setString(6, object.getCurrentVillage());
        statement.setInt(7, object.getOriginLocation().getId());
        statement.setString(8, object.getOriginDistrict());
        statement.setString(9, object.getOriginVillage());
        statement.setInt(10, object.getGraduateYear());
        statement.setString(11, object.getTazkiraId());
        statement.setString(12, object.getLanguage().getKey());
        return statement;
    }
}

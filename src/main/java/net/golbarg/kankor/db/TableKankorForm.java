package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.KankorForm;
import net.golbarg.kankor.model.Language;
import net.golbarg.kankor.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableKankorForm implements CRUDHandler<KankorForm> {
    public static final String TABLE_NAME = "KANKOR_FORMS";
    public static final String COLUMNS_STR = "ID, USER_ID, NAME, LAST_NAME, FATHER_NAME, GRAND_FATHER_NAME, CURRENT_PROVINCE, CURRENT_DISTRICT, CURRENT_VILLAGE, ORIGINAL_PROVINCE, ORIGINAL_DISTRICT, ORIGINAL_VILLAGE, GRADUATE_YEAR, SCHOOL_NAME, TAZKIRA_ID, GENDER, LANGUAGE";

    @Override
    public boolean create(KankorForm object) {
        String query = String.format("insert into %s (USER_ID, NAME, LAST_NAME, FATHER_NAME, GRAND_FATHER_NAME, CURRENT_PROVINCE, CURRENT_DISTRICT, CURRENT_VILLAGE, ORIGINAL_PROVINCE, ORIGINAL_DISTRICT, ORIGINAL_VILLAGE, GRADUATE_YEAR, SCHOOL_NAME, TAZKIRA_ID, GENDER, LANGUAGE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", TABLE_NAME);

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
    public KankorForm findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        KankorForm object = null;

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
    public ArrayList<KankorForm> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<KankorForm> locationList = new ArrayList<>();
        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                KankorForm location = mapColumn(result);
                locationList.add(location);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return locationList;
    }

    @Override
    public boolean update(KankorForm object) {
        String query = String.format("update %s set USER_ID = ?, NAME = ?, LAST_NAME = ?, FATHER_NAME = ?, GRAND_FATHER_NAME = ?, CURRENT_PROVINCE = ?, CURRENT_DISTRICT = ?, CURRENT_VILLAGE = ?, ORIGINAL_PROVINCE = ?, ORIGINAL_DISTRICT = ?, ORIGINAL_VILLAGE = ?, GRADUATE_YEAR = ?, SCHOOL_NAME = ?, TAZKIRA_ID = ?, GENDER = ?, LANGUAGE = ? where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(17, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(KankorForm object) {
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
    public KankorForm mapColumn(ResultSet result) throws SQLException {
        return new KankorForm(
                result.getInt("ID"),
                result.getInt("USER_ID"),
                result.getString("NAME"),
                result.getString("LAST_NAME"),
                result.getString("FATHER_NAME"),
                result.getString("GRAND_FATHER_NAME"),
                new Location(result.getInt("CURRENT_PROVINCE"), 0, "", 0, "", ""),
                result.getString("CURRENT_DISTRICT"),
                result.getString("CURRENT_VILLAGE"),
                new Location(result.getInt("ORIGINAL_PROVINCE"), 0, "", 0, "", ""),
                result.getString("ORIGINAL_DISTRICT"),
                result.getString("ORIGINAL_VILLAGE"),
                result.getInt("GRADUATE_YEAR"),
                result.getString("SCHOOL_NAME"),
                result.getString("TAZKIRA_ID"),
                Gender.getGender(result.getString("GENDER")),
                Language.getLanguage(result.getString("LANGUAGE"))
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, KankorForm object) throws SQLException {
        statement.setInt(1, object.getUserId());
        statement.setString(2, object.getName());
        statement.setString(3, object.getLastName());
        statement.setString(4, object.getFatherName());
        statement.setString(5, object.getGrandFatherName());
        statement.setInt(6, object.getCurrentLocation().getId());
        statement.setString(7, object.getCurrentDistrict());
        statement.setString(8, object.getCurrentVillage());
        statement.setInt(9, object.getOriginLocation().getId());
        statement.setString(10, object.getOriginDistrict());
        statement.setString(11, object.getOriginVillage());
        statement.setInt(12, object.getGraduateYear());
        statement.setString(13, object.getSchoolName());
        statement.setString(14, object.getTazkiraId());
        statement.setString(15, object.getGender().getKey());
        statement.setString(16, object.getLanguage().getKey());

        return statement;
    }
}

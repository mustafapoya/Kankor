package net.golbarg.kankor.db;

import net.golbarg.kankor.model.KankorResult;
import net.golbarg.kankor.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableKankorResult implements CRUDHandler<KankorResult> {
    public static final String TABLE_NAME = "KANKOR_RESULTS";
    public static final String COLUMNS_STR = "ID, KANKOR_ID, NAME, FATHER_NAME, GRAND_FATHER_NAME, SCHOOL, PROVINCE, SCORE, RESULT";

    @Override
    public boolean create(KankorResult object) {
        String query = String.format("insert into %s (KANKOR_ID, NAME, FATHER_NAME, GRAND_FATHER_NAME, SCHOOL, PROVINCE, SCORE, RESULT) values (?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
    public KankorResult findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        KankorResult object = null;

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
    public ArrayList<KankorResult> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<KankorResult> resultList = new ArrayList<>();
        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                KankorResult object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(KankorResult object) {
        String query = String.format("update %s set KANKOR_ID = ?, NAME = ?, FATHER_NAME = ?, GRAND_FATHER_NAME = ?, SCHOOL = ?, PROVINCE = ?, SCORE = ?, RESULT = ? where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(9, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(KankorResult object) {
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
    public KankorResult mapColumn(ResultSet result) throws SQLException {
        return new KankorResult(
                result.getInt("ID"),
                result.getString("KANKOR_ID"),
                result.getString("NAME"),
                result.getString("FATHER_NAME"),
                result.getString("GRAND_FATHER_NAME"),
                result.getString("SCHOOL"),
                result.getString("PROVINCE"),
                result.getDouble("SCORE"),
                result.getString("RESULT")
        );

    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, KankorResult object) throws SQLException {
        statement.setString(1, object.getKankorId());
        statement.setString(2, object.getName());
        statement.setString(3, object.getFatherName());
        statement.setString(4, object.getGrandFatherName());
        statement.setString(5, object.getSchoolName());
        statement.setString(6, object.getProvince());
        statement.setDouble(7, object.getScore());
        statement.setString(8, object.getResult());
        return statement;
    }
}

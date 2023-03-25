package net.golbarg.kankor.db;

import net.golbarg.kankor.model.ExamResult;
import net.golbarg.kankor.model.KankorResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableExamResult implements CRUDHandler<ExamResult> {
    public static final String TABLE_NAME = "EXAM_RESULTS";
    public static final String COLUMNS_STR = "ID, EXAM_ID, NAME, SCHOOL, PROVINCE, SCORE, PASSED_FIELD, EXAM_DATE, EXAM_DURATION";

    @Override
    public boolean create(ExamResult object) {
        String query = String.format("insert into %s (EXAM_ID, NAME, SCHOOL, PROVINCE, SCORE, PASSED_FIELD, EXAM_DATE, EXAM_DURATION) values (?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
    public ExamResult findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        ExamResult object = null;

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
    public ArrayList<ExamResult> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<ExamResult> resultList = new ArrayList<>();
        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                ExamResult object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(ExamResult object) {
        String query = String.format("update %s set EXAM_ID = ?, NAME = ?, SCHOOL = ?, PROVINCE = ?, SCORE = ?, PASSED_FIELD = ?, EXAM_DATE = ?, EXAM_DURATION = ? where id = ?", TABLE_NAME);

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
    public boolean delete(ExamResult object) {
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
    public ExamResult mapColumn(ResultSet result) throws SQLException {
        , SCHOOL, PROVINCE, SCORE, PASSED_FIELD, EXAM_DATE, EXAM_DURATION
        return new ExamResult(
                result.getInt("ID"),
                result.getInt("EXAM_ID"),
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
    public PreparedStatement putValues(PreparedStatement statement, ExamResult object) throws SQLException {
        return null;
    }
}

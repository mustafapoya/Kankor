package net.golbarg.kankor.db;

import net.golbarg.kankor.model.ExamCorrectAnswerCount;
import net.golbarg.kankor.model.ExamResult;

import java.sql.*;
import java.util.ArrayList;

public class TableExamResult implements CRUDHandler<ExamResult> {
    public static final String TABLE_NAME = "EXAM_RESULTS";
    public static final String [] COLUMNS =
            {"ID", "EXAM_ID", "EXAM_DURATION", "MATH_CORRECT", "NATURAL_CORRECT", "SOCIAL_CORRECT", "ALSANA_CORRECT", "PASSED_FIELD"};
    public static final String COLUMNS_STR =
            "ID, EXAM_ID, EXAM_DURATION, MATH_CORRECT, NATURAL_CORRECT, SOCIAL_CORRECT, ALSANA_CORRECT, PASSED_FIELD";

    TableExam tableExam;
    public TableExamResult() {
        tableExam = new TableExam();
    }

    @Override
    public boolean create(ExamResult object) {
        String query = String.format("insert into %s (EXAM_ID, EXAM_DURATION, MATH_CORRECT, NATURAL_CORRECT, SOCIAL_CORRECT, ALSANA_CORRECT, PASSED_FIELD) values (?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
        String query = String.format("update %s set EXAM_ID = ?, EXAM_DURATION = ?, MATH_CORRECT = ?, " +
                                     "NATURAL_CORRECT = ?, SOCIAL_CORRECT = ?, ALSANA_CORRECT = ?, PASSED_FIELD = ? " +
                                     "where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(8, object.getId());
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
    public ExamResult mapColumn(ResultSet result) throws SQLException {
        ExamCorrectAnswerCount correctAnswerCount =
                new ExamCorrectAnswerCount(
                        result.getInt("MATH_CORRECT"),
                        result.getInt("NATURAL_CORRECT"),
                        result.getInt("SOCIAL_CORRECT"),
                        result.getInt("ALSANA_CORRECT")
                );
        return new ExamResult(
                result.getInt("ID"),
                tableExam.findById(result.getInt("EXAM_ID")),
                result.getLong("EXAM_DURATION"),
                correctAnswerCount,
                result.getString("PASSED_FIELD")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, ExamResult object) throws SQLException {
        statement.setInt(1, object.getExam().getId());
        statement.setLong(2, object.getExamDuration());
        statement.setDouble(3, object.getCorrectAnswerCount().getMath());
        statement.setDouble(4, object.getCorrectAnswerCount().getNatural());
        statement.setDouble(5, object.getCorrectAnswerCount().getSocial());
        statement.setDouble(6, object.getCorrectAnswerCount().getAlsana());
        statement.setString(7, object.getPassedField());

        return statement;
    }
}

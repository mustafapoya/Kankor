package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Exam;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TableExam implements CRUDHandler<Exam> {
    public static final String TABLE_NAME = "EXAMS";
    public static final String [] COLUMNS =
            {"ID", "USER_ID", "EXAM_DATE", "EXAM_DURATION", "MATH_SCORE", "NATURAL_SCORE", "SOCIAL_SCORE", "ALSANA_SCORE", "PASSED_FIELD"};
    public static final String COLUMNS_STR =
            "ID, USER_ID, EXAM_DATE, EXAM_DURATION, MATH_SCORE, NATURAL_SCORE, SOCIAL_SCORE, ALSANA_SCORE, PASSED_FIELD";

    @Override
    public boolean create(Exam object) {
        String query = String.format("insert into %s (USER_ID, EXAM_DATE, EXAM_DURATION, MATH_SCORE, NATURAL_SCORE, SOCIAL_SCORE, ALSANA_SCORE, PASSED_FIELD) values (?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
    public Exam findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Exam object = null;

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
    public ArrayList<Exam> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Exam> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Exam object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Exam object) {
        String query = String.format("update %s set USER_ID = ?, EXAM_DATE = ?, EXAM_DURATION = ?, MATH_SCORE = ?, " +
                                     "NATURAL_SCORE = ?, SOCIAL_SCORE = ?, ALSANA_SCORE = ?, PASSED_FIELD = ? " +
                                     "where id = ?", TABLE_NAME);

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
    public boolean delete(Exam object) {
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
    public Exam mapColumn(ResultSet result) throws SQLException {
        return new Exam(
                result.getInt("ID"),
                result.getInt("USER_ID"),
                result.getDate("EXAM_DATE").toLocalDate(),
                result.getLong("EXAM_DURATION"),
                result.getDouble("MATH_SCORE"),
                result.getDouble("NATURAL_SCORE"),
                result.getDouble("SOCIAL_SCORE"),
                result.getDouble("ALSANA_SCORE"),
                result.getString("PASSED_FIELD")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Exam object) throws SQLException {
        statement.setInt(1, object.getUserId());
        statement.setDate(2, Date.valueOf(object.getExamDate()));
        statement.setLong(3, object.getExamDuration());
        statement.setDouble(4, object.getMathScore());
        statement.setDouble(5, object.getNaturalScore());
        statement.setDouble(6, object.getSocialScore());
        statement.setDouble(7, object.getAlsanaScore());
        statement.setString(8, object.getPassedField());

        return statement;
    }
}

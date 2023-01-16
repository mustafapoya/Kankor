package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Config;
import net.golbarg.kankor.model.Email;
import net.golbarg.kankor.model.Exam;

import java.sql.*;
import java.util.ArrayList;

public class TableExam implements CRUDHandler<Exam> {
    public static final String TABLE_NAME = "EXAMS";
    public static final String [] COLUMNS = {"ID", "USER_ID", "KANKOR_ID", "EXAM_DATE", "EXAM_DURATION", "MATH_GRADE",
                                             "NATURAL_GRADE", "SOCIAL_GRADE", "ALSANA_GRADE", "EXAM_GRADE", "EXAM_PASSEDFIELD"};
    public static final String COLUMNS_STR = "ID, USER_ID, KONKOR_ID, EXAM_DATE, EXAM_DURATION, MATH_GRADE, NATURAL_GRADE, SOCIAL_GRADE, ALSANA_GRADE, EXAM_GRADE, EXAM_PASSEDFIELD";


    @Override
    public boolean create(Exam object) {
        String query = String.format("insert into %s (USER_ID, KONKOR_ID, EXAM_DATE, EXAM_DURATION, MATH_GRADE, NATURAL_GRADE, SOCIAL_GRADE, ALSANA_GRADE, EXAM_GRADE, EXAM_PASSEDFIELD) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
        String query = String.format("update %s set USER_ID = ?, KONKOR_ID = ?, EXAM_DATE = ?, EXAM_DURATION = ?, MATH_GRADE = ?, " +
                                     "NATURAL_GRADE = ?, SOCIAL_GRADE = ?, ALSANA_GRADE = ?, EXAM_GRADE = ?, EXAM_PASSEDFIELD = ? " +
                                     "where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(11, object.getId());
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
                result.getString("KONKOR_ID"),
                result.getDate("EXAM_DATE"),
                result.getDate("EXAM_DURATION"),
                result.getDouble("MATH_GRADE"),
                result.getDouble("NATURAL_GRADE"),
                result.getDouble("SOCIAL_GRADE"),
                result.getDouble("ALSANA_GRADE"),
                result.getDouble("EXAM_GRADE"),
                result.getString("EXAM_PASSEDFIELD")

        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Exam object) throws SQLException {
        statement.setInt(1, object.getUserId());
        statement.setString(2, object.getKankorId());
        statement.setDate(3, Date.valueOf(object.getExamDate().toString()));
        statement.setDate(4, Date.valueOf(object.getExamDuration().toString()));
        statement.setDouble(5, object.getMathGrade());
        statement.setDouble(6, object.getNaturalGrade());
        statement.setDouble(7, object.getSocialGrade());
        statement.setDouble(8, object.getAlsanaGrade());
        statement.setDouble(9, object.getExamGrade());
        statement.setString(10, object.getExamPassedField());

        return statement;
    }
}

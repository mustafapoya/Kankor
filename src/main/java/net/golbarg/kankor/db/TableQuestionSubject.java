package net.golbarg.kankor.db;

import net.golbarg.kankor.model.QuestionSubject;
import net.golbarg.kankor.model.Resource;
import net.golbarg.kankor.model.ResourceCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableQuestionSubject implements CRUDHandler<QuestionSubject> {
    public static final String TABLE_NAME = "QUESTION_SUBJECTS";
    public static final String [] COLUMNS = {"ID", "SUBJECT_TYPE", "SUBJECT_KEY", "SUBJECT_TITLE_PE"};
    public static final String COLUMNS_STR = "ID, SUBJECT_TYPE, SUBJECT_KEY, SUBJECT_TITLE_PE";

    @Override
    public boolean create(QuestionSubject object) {
        String query = String.format("insert into %s (SUBJECT_TYPE, SUBJECT_KEY, SUBJECT_TITLE_PE) values (?, ?, ?)", TABLE_NAME);

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
    public QuestionSubject findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        QuestionSubject object = null;

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
    public ArrayList<QuestionSubject> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<QuestionSubject> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                resultList.add(mapColumn(result));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(QuestionSubject object) {
        String query = String.format("update %s set SUBJECT_TYPE = ?, SUBJECT_KEY = ?, SUBJECT_TITLE_PE = ? where id = ?", TABLE_NAME);

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(4, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(QuestionSubject object) {
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
    public QuestionSubject mapColumn(ResultSet result) throws SQLException {
        return new QuestionSubject(
                result.getInt("ID"),
                result.getString("SUBJECT_TYPE"),
                result.getString("SUBJECT_KEY"),
                result.getString("SUBJECT_TITLE_PE")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, QuestionSubject object) throws SQLException {
        statement.setString(1, object.getType());
        statement.setString(2, object.getKey());
        statement.setString(3, object.getTitlePersian());
        return statement;
    }
}

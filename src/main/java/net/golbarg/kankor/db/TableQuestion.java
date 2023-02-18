package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Faculty;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.QuestionSubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableQuestion implements CRUDHandler<Question>{
    public static final String TABLE_NAME = "QUESTIONS";
    public static final String [] COLUMNS = {"ID", "SUBJECT_ID", "QUESTION", "CHOICE1", "CHOICE2", "CHOICE3", "CHOICE4", "CORRECT_CHOICE", "RELATED_CLASS", "QUESTION_TYPE", "QUESTION_UPDATE"};
    public static final String COLUMNS_STR = "ID, SUBJECT_ID, QUESTION, CHOICE1, CHOICE2, CHOICE3, CHOICE4, CORRECT_CHOICE, RELATED_CLASS, QUESTION_TYPE, QUESTION_UPDATE";


    @Override
    public boolean create(Question object) {
        String query = String.format("insert into %s (SUBJECT_ID, QUESTION, CHOICE1, CHOICE2, CHOICE3, CHOICE4, CORRECT_CHOICE, RELATED_CLASS, QUESTION_TYPE, QUESTION_UPDATE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
    public Question findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Question object = null;

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
    public ArrayList<Question> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Question> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Question object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<Question> getQuestionOf(int subjectId) {
        String query = String.format("SELECT %s FROM %s WHERE SUBJECT_ID = ?;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Question> resultList = new ArrayList<>();

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, subjectId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Question object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<Question> getQuestionsOf(int [] subjectIds, int count) {
        StringBuilder queryBuilder = new StringBuilder("SELECT " + COLUMNS_STR + " FROM " + TABLE_NAME +
                                                        " WHERE SUBJECT_ID IN ");

        queryBuilder.append("(");
        for(int subjectId : subjectIds) {
            queryBuilder.append("?, ");
        }

        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        queryBuilder.append(") ");
        queryBuilder.append(" ORDER BY RAND() LIMIT ?;");

        String query = queryBuilder.toString();

        ArrayList<Question> resultList = new ArrayList<>();

        try {

            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for(int i = 0; i < subjectIds.length; i++) {
                statement.setInt(i+1, subjectIds[i]);
            }

            statement.setInt(subjectIds.length+1, count);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Question object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Question object) {
        String query = String.format("update %s set SUBJECT_ID = ?, QUESTION = ?, CHOICE1 = ?, CHOICE2 = ?, CHOICE3 = ?, CHOICE4 = ?, CORRECT_CHOICE = ?, RELATED_CLASS = ?, QUESTION_TYPE = ?, QUESTION_UPDATE = ? where id = ?", TABLE_NAME);

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
    public boolean delete(Question object) {
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
    public Question mapColumn(ResultSet result) throws SQLException {
        return new Question(
                result.getInt("ID"),
                new QuestionSubject(result.getInt("SUBJECT_ID"), "", "", ""),
                result.getString("QUESTION"),
                result.getString("CHOICE1"),
                result.getString("CHOICE2"),
                result.getString("CHOICE3"),
                result.getString("CHOICE4"),
                result.getInt("CORRECT_CHOICE"),
                result.getString("RELATED_CLASS"),
                result.getString("QUESTION_TYPE"),
                result.getInt("QUESTION_UPDATE")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Question object) throws SQLException {
        statement.setString(1, object.getQuestion());
        statement.setInt(8, object.getSubject().getId());
        statement.setString(2, object.getChoice1());
        statement.setString(3, object.getChoice2());
        statement.setString(4, object.getChoice3());
        statement.setString(5, object.getChoice4());
        statement.setInt(6, object.getCorrectChoice());
        statement.setString(7, object.getRelatedClass());
        statement.setString(9, object.getQuestionType());
        statement.setInt(10, object.getQuestionUpdate());
        return statement;
    }
}

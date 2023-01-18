package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Email;
import net.golbarg.kankor.model.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableFaculty implements CRUDHandler<Faculty>{
    public static final String TABLE_NAME = "FACULTIES";
    public static final String [] COLUMNS = {"ID", "NAME", "DEPARTMENT", "CODE", "MINIMUM_GRADE", "UNI_ID", "ADMISSION"};
    public static final String COLUMNS_STR = "ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNI_ID, ADMISSION";

    @Override
    public boolean create(Faculty object) {
        String query = String.format("insert into %s (NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNI_ID, ADMISSION) values (?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
    public Faculty findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Faculty object = null;

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
    public ArrayList<Faculty> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Faculty> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Faculty object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Faculty object) {
        String query = String.format("update %s set NAME = ?, DEPARTMENT = ?, CODE = ?, MINIMUM_GRADE = ?, UNI_ID = ?, ADMISSION = ? where id = ?", TABLE_NAME);

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
    public boolean delete(Faculty object) {
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
    public Faculty mapColumn(ResultSet result) throws SQLException {
        return new Faculty(
                result.getInt("ID"),
                result.getString("NAME"),
                result.getString("DEPARTMENT"),
                result.getString("CODE"),
                result.getInt("MINIMUM_GRADE"),
                result.getInt("UNI_ID"),
                result.getInt("ADMISSION")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Faculty object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDepartment());
        statement.setString(3, object.getCode());
        statement.setInt(4, object.getMinimumGrade());
        statement.setInt(5, object.getUniversityId());
        statement.setInt(6, object.getAdmission());
        return statement;
    }
}

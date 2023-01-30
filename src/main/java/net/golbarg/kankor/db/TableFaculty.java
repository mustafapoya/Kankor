package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Email;
import net.golbarg.kankor.model.Faculty;
import net.golbarg.kankor.model.University;

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
        String query = String.format("SELECT FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNI_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION FROM %s join UNIVERSITIES ON UNIVERSITIES.ID = FACULTIES.UNI_ID where FACULTIES.id = ?;", TABLE_NAME);

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

    public ArrayList<Faculty> findByName(String facultyName) {
        String query = String.format("SELECT FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNI_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION " +
                                     "FROM %s join UNIVERSITIES ON UNIVERSITIES.ID = FACULTIES.UNI_ID " +
                                     "where FACULTIES.NAME like ?;", TABLE_NAME);

        ArrayList<Faculty> resultList = new ArrayList<>();

        try {

            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, facultyName + "%");

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Faculty object = mapColumn(result);
                resultList.add(object);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return resultList;
    }

    public ArrayList<Faculty> getFacultiesOf(int universityId) {
        String query = String.format("SELECT FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNI_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION " +
                "FROM %s join UNIVERSITIES ON UNIVERSITIES.ID = FACULTIES.UNI_ID " +
                "where FACULTIES.UNI_ID = ?;", TABLE_NAME);

        ArrayList<Faculty> resultList = new ArrayList<>();

        try {

            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, universityId);

            ResultSet result = statement.executeQuery();

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
    public ArrayList<Faculty> getAll() {
        String query = String.format("SELECT FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNI_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION FROM %s join UNIVERSITIES ON UNIVERSITIES.ID = FACULTIES.UNI_ID;", TABLE_NAME);

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

    public ArrayList<String> getDistinctFaculties() {
        String query = String.format("SELECT DISTINCT NAME FROM %s;", TABLE_NAME);

        ArrayList<String> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                resultList.add(result.getString("NAME"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return resultList;
    }

    public ArrayList<Faculty> getFacultiesByCode(String [] codes) {
        String query = String.format(" SELECT FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNI_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION " +
                                     " FROM %s join UNIVERSITIES ON UNIVERSITIES.ID = FACULTIES.UNI_ID; " +
                                     " WHERE FACULTIES.CODE IN (?, ?, ?, ?, ?) ORDER BY FACULTIES.MINIMUM_GRADE DESC; ", TABLE_NAME);

        ArrayList<Faculty> resultList = new ArrayList<>();

        try {

            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for(int i = 1; i <= codes.length; i++) {
                if(i > 6) {
                    break;
                }
                statement.setString(i + 1, codes[i]);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                resultList.add(mapColumn(resultSet));
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
                new University(result.getInt("UNI_ID"),result.getString("UNI_TITLE")),
                result.getInt("ADMISSION")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Faculty object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDepartment());
        statement.setString(3, object.getCode());
        statement.setInt(4, object.getMinimumGrade());
        statement.setInt(5, object.getUniversity().getId());
        statement.setInt(6, object.getAdmission());
        return statement;
    }
}

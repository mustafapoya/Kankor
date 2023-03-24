package net.golbarg.kankor.db;

import net.golbarg.kankor.model.UniversityFaculty;
import net.golbarg.kankor.model.University;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableUniversityFaculty implements CRUDHandler<UniversityFaculty>{
    public static final String TABLE_NAME = "UNIVERSITY_FACULTIES";
    public static final String [] COLUMNS = {"ID", "UNIVERSITY_ID", "NAME", "DEPARTMENT", "CODE", "MINIMUM_GRADE", "ADMISSION"};
    public static final String COLUMNS_STR = "ID, UNIVERSITY_ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, ADMISSION";

    @Override
    public boolean create(UniversityFaculty object) {
        String query = String.format("insert into %s (UNIVERSITY_ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, ADMISSION) values (?, ?, ?, ?, ?, ?)", TABLE_NAME);

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
    public UniversityFaculty findById(int id) {
        String query = String.format("SELECT UNIVERSITY_FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, " +
                                     "UNIVERSITY_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION FROM %s " +
                                     "join UNIVERSITIES ON UNIVERSITIES.ID = UNIVERSITY_FACULTIES.UNIVERSITY_ID " +
                                     "where UNIVERSITY_FACULTIES.id = ?;", TABLE_NAME);

        UniversityFaculty object = null;

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

    public ArrayList<UniversityFaculty> findByName(String facultyName) {
        String query = String.format("SELECT UNIVERSITY_FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNIVERSITY_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION " +
                                     "FROM %s join UNIVERSITIES ON UNIVERSITIES.ID = UNIVERSITY_FACULTIES.UNIVERSITY_ID " +
                                     "where UNIVERSITY_FACULTIES.NAME like ?;", TABLE_NAME);

        ArrayList<UniversityFaculty> resultList = new ArrayList<>();

        try {

            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, facultyName + "%");

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UniversityFaculty object = mapColumn(result);
                resultList.add(object);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return resultList;
    }

    public ArrayList<UniversityFaculty> getFacultiesOf(int universityId) {
        String query = String.format("SELECT UNIVERSITY_FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNIVERSITY_ID, " +
                                     "UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION " +
                                     "FROM %s join UNIVERSITIES ON UNIVERSITIES.ID = UNIVERSITY_FACULTIES.UNIVERSITY_ID " +
                                     "where UNIVERSITY_FACULTIES.UNIVERSITY_ID = ?;", TABLE_NAME);

        ArrayList<UniversityFaculty> resultList = new ArrayList<>();

        try {

            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, universityId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UniversityFaculty object = mapColumn(result);
                resultList.add(object);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return resultList;
    }


    @Override
    public ArrayList<UniversityFaculty> getAll() {
        String query = String.format("SELECT UNIVERSITY_FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, " +
                                     "UNIVERSITY_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION FROM %s " +
                                     "join UNIVERSITIES ON UNIVERSITIES.ID = UNIVERSITY_FACULTIES.UNIVERSITY_ID;", TABLE_NAME);

        ArrayList<UniversityFaculty> resultList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                UniversityFaculty object = mapColumn(result);
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

    public ArrayList<UniversityFaculty> getFacultiesByCode(String [] codes) {
        String query = String.format(" SELECT UNIVERSITY_FACULTIES.ID, NAME, DEPARTMENT, CODE, MINIMUM_GRADE, UNIVERSITY_ID, UNIVERSITIES.TITLE AS UNI_TITLE, ADMISSION " +
                                     " FROM %s join UNIVERSITIES ON UNIVERSITIES.ID = UNIVERSITY_FACULTIES.UNIVERSITY_ID " +
                                     " WHERE UNIVERSITY_FACULTIES.CODE IN (?, ?, ?, ?, ?) ORDER BY UNIVERSITY_FACULTIES.MINIMUM_GRADE DESC; ", TABLE_NAME);

        ArrayList<UniversityFaculty> resultList = new ArrayList<>();

        try {

            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for(int i = 0; i < codes.length; i++) {
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
    public boolean update(UniversityFaculty object) {
        String query = String.format("update %s set UNIVERSITY_ID = ?, NAME = ?, DEPARTMENT = ?, CODE = ?, MINIMUM_GRADE = ?, ADMISSION = ? where id = ?", TABLE_NAME);

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
    public boolean delete(UniversityFaculty object) {
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
    public UniversityFaculty mapColumn(ResultSet result) throws SQLException {
        return new UniversityFaculty(
                result.getInt("ID"),
                new University(result.getInt("UNIVERSITY_ID"),result.getString("UNI_TITLE")),
                result.getString("NAME"),
                result.getString("DEPARTMENT"),
                result.getString("CODE"),
                result.getInt("MINIMUM_GRADE"),
                result.getInt("ADMISSION")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, UniversityFaculty object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setInt(2, object.getUniversity().getId());
        statement.setString(3, object.getDepartment());
        statement.setString(4, object.getCode());
        statement.setInt(5, object.getMinimumGrade());
        statement.setInt(6, object.getAdmission());
        return statement;
    }
}

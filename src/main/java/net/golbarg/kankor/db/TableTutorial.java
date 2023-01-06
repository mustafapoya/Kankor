package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.Tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableTutorial implements CRUDHandler<Tutorial> {
    public static final String TABLE_NAME = "TUTORIALS";
    public static final String COLUMNS_STR = "ID, TITLE";

    @Override
    public boolean create(Tutorial object) {
        return false;
    }

    @Override
    public Tutorial findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Tutorial object = null;

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
    public ArrayList<Tutorial> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<Tutorial> resultList = new ArrayList<>();
        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Tutorial object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Tutorial object) {
        return false;
    }

    @Override
    public boolean delete(Tutorial object) {
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
    public Tutorial mapColumn(ResultSet result) throws SQLException {
        return new Tutorial(
                result.getInt("ID"),
                result.getString("TITLE")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Tutorial object) throws SQLException {
        statement.setString(1, object.getTitle());
        return statement;
    }
}

package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Tutorial;
import net.golbarg.kankor.model.TutorialDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableTutorialDetail implements CRUDHandler<TutorialDetail> {
    public static final String TABLE_NAME = "TUTORIAL_DETAILS";
    public static final String COLUMNS_STR = "ID, TUTORIAL_ID, TITLE, SHORT_DESC, FULL_DESC, SHORT_DESC_ICON, ICON_NAME";

    @Override
    public boolean create(TutorialDetail object) {
        return false;
    }

    @Override
    public TutorialDetail findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        TutorialDetail object = null;

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
    public ArrayList<TutorialDetail> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<TutorialDetail> resultList = new ArrayList<>();
        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                TutorialDetail object = mapColumn(result);
                resultList.add(object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(TutorialDetail object) {
        String query = String.format("update %s set TUTORIAL_ID = ?, TITLE = ?, SHORT_DESC = ?, FULL_DESC = ?, SHORT_DESC_ICON = ?, ICON_NAME = ? where id = ?", TABLE_NAME);

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
    public boolean delete(TutorialDetail object) {
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
    public TutorialDetail mapColumn(ResultSet result) throws SQLException {
        return new TutorialDetail(
                result.getInt("ID"),
                result.getInt("TUTORIAL_ID"),
                result.getString("TITLE"),
                result.getString("SHORT_DESC"),
                result.getString("FULL_DESC"),
                result.getString("SHORT_DESC_ICON"),
                result.getString("ICON_NAME")
        );
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, TutorialDetail object) throws SQLException {
        statement.setInt(1, object.getTutorialId());
        statement.setString(2, object.getTitle());
        statement.setString(3, object.getShortDescription());
        statement.setString(4, object.getFullDescription());
        statement.setString(5, object.getShortDescriptionIcon());
        statement.setString(6, object.getIcon());

        return statement;
    }
}

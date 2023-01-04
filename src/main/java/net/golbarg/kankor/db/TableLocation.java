package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableLocation implements CRUDHandler<Location> {
    public static final String TABLE_NAME = "LOCATIONS";
    public static final String COLUMNS_STR = "ID, TYPE, ZONE, PARENT_ID, NAME, NAME_DA";

    @Override
    public boolean create(Location object) {
        String query = "insert into locations (TYPE, ZONE, PARENT_ID, NAME, NAME_DA) values (?, ?, ?, ?, ?)";

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

    public Location findById(int id) {
        String query = String.format("SELECT %s FROM %s where id = ?;", COLUMNS_STR, TABLE_NAME);

        Location location = null;

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                location = mapColumn(resultSet);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return location;
    }
    @Override
    public ArrayList<Location> getAll() {
        String query = String.format("SELECT %s FROM %s;", COLUMNS_STR, TABLE_NAME);
        ArrayList<Location> locationList = new ArrayList<>();
        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Location location = mapColumn(result);
                locationList.add(location);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return locationList;
    }

    public ArrayList<Location> getAllProvince() {
        String query = String.format("SELECT %s FROM %s WHERE type = 1 and ID > 1 ORDER BY NAME ASC;", COLUMNS_STR, TABLE_NAME);

        ArrayList<Location> locationList = new ArrayList<>();

        try {
            ResultSet result = DBController.executeQuery(query);
            while (result.next()) {
                Location location = mapColumn(result);
                locationList.add(location);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return locationList;
    }

    @Override
    public boolean update(Location object) {
        String query = "update locations set TYPE = ?, ZONE = ?, PARENT_ID = ?, NAME = ?, NAME_DA = ? where id = ?";

        try {
            Connection connection = DBController.getLocalConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement = putValues(statement, object);
            statement.setInt(6, object.getId());
            statement.executeUpdate();

            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Location object) {
        String query = "DELETE from locations where id = ?";

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
    public Location mapColumn(ResultSet result) throws SQLException {
        return new Location(
                result.getInt("ID"),
                result.getInt("TYPE"),
                result.getString("ZONE"),
                result.getInt("PARENT_ID"),
                result.getString("NAME"),
                result.getString("NAME_DA")
        );
    }

    public PreparedStatement putValues(PreparedStatement statement, Location object) throws SQLException {
        statement.setInt(1, object.getType());
        statement.setString(2, object.getZone());
        statement.setInt(3, object.getParentId());
        statement.setString(4, object.getName());
        statement.setString(5, object.getPersianName());
        return statement;
    }
}

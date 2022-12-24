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
    public boolean create(Location object) {
        return false;
    }

    @Override
    public boolean update(Location object) {
        return false;
    }

    @Override
    public boolean delete(Location object) {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean emptyTable() {
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
}

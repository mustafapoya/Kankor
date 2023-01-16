package net.golbarg.kankor.db;

import net.golbarg.kankor.model.Faculty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableFaculty implements CRUDHandler<Faculty>{
    public static final String TABLE_NAME = "EMAILS";
    public static final String [] COLUMNS = {"ID", "EMAIL", "PHONE", "TITLE", "CONTENT"};
    public static final String COLUMNS_STR = "ID, EMAIL, PHONE, TITLE, CONTENT";


    @Override
    public boolean create(Faculty object) {
        return false;
    }

    @Override
    public Faculty findById(int id) {
        return null;
    }

    @Override
    public ArrayList<Faculty> getAll() {
        return null;
    }

    @Override
    public boolean update(Faculty object) {
        return false;
    }

    @Override
    public boolean delete(Faculty object) {
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
    public Faculty mapColumn(ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement putValues(PreparedStatement statement, Faculty object) throws SQLException {
        return null;
    }
}

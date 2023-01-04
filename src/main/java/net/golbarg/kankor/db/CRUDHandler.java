package net.golbarg.kankor.db;

import net.golbarg.kankor.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUDHandler<ModelClass> {
    public boolean create(ModelClass object);
    public ModelClass findById(int id);
    public ArrayList<ModelClass> getAll();
    public boolean update(ModelClass object);
    public boolean delete(ModelClass object);
    public int getCount();
    public boolean emptyTable();
    public ModelClass mapColumn(ResultSet result) throws SQLException;
    public PreparedStatement putValues(PreparedStatement statement, ModelClass object) throws SQLException;
}

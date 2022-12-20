package net.golbarg.kankor.kankor.db;

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
    public ModelClass mapColumn(ResultSet result) throws SQLException;
    public boolean emptyTable();
}

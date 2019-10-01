package ua.training.model.dao.mysql.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface Mapper <T> {
    T map(ResultSet set) throws SQLException;
}

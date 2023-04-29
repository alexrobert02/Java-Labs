package org.example;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    void create(T obj) throws SQLException;
    List<T> findAll() throws SQLException;
    T findById(int id) throws SQLException;
    T findByName(String name) throws SQLException;
}

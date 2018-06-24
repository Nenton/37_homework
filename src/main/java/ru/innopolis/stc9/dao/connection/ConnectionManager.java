package ru.innopolis.stc9.dao.connection;

import java.sql.Connection;

/**
 * Connection for DB
 */
public interface ConnectionManager {
    Connection getConnection();
}

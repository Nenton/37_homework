package ru.innopolis.stc9.dao.connection;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.hibernate.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class TestDaoJdbc {

    public void saveTest(Test test) {
        try (Connection connection = ConnectionManagerJDBCImpl.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO test(id, testdate, testdouble, testint) VALUES (?, ?, ?, ?)")) {
                statement.setString(1, test.getId());
                statement.setDate(2, test.getTestDate());
                statement.setDouble(3, test.getTestDouble());
                statement.setInt(4, test.getTestInt());
                statement.execute();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

package ru.innopolis.stc9.dao.connection;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.hibernate.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Test> readJdbc() {
        List<Test> tests = new ArrayList<>();
        try (Connection connection = ConnectionManagerJDBCImpl.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select * from test")) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        Date date = resultSet.getDate("testdate");
                        double v = resultSet.getDouble("testdouble");
                        int i = resultSet.getInt("testint");
                        tests.add(new Test(id, i, v, date));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tests;
    }
}

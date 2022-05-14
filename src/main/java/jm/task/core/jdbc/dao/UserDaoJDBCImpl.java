package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDaoJDBCImpl extends Util implements UserDao {

    private final   String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
           "  id BIGINT NOT NULL AUTO_INCREMENT," +
           "  Name VARCHAR(45) NOT NULL," +
           "  LastName VARCHAR(45) NOT NULL," +
           "  Age TINYINT NOT NULL," +
           "  PRIMARY KEY (id))";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("DROP TABLE users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User removeUserById(long id) {
        try (PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("DELETE FROM users WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();

        try (Statement statement = Util.getConnection().createStatement()) {
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

     public void cleanUsersTable() {
        try (PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("DELETE FROM users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45),lastname VARCHAR(45),age TINYINT)   ");
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute("DROP TABLE users ");
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname,age) Values (?,?,?)";
        try (PreparedStatement statement = new Util().getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE  FROM users WHERE id=?";
        try (PreparedStatement statement = new Util().getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = new Util().getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                String lastname = resultSet.getString("LASTNAME");
                byte age = resultSet.getByte("AGE");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setAge(age);

                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {

        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {

        }
    }
}

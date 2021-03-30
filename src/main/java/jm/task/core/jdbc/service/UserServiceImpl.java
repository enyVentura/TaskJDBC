package jm.task.core.jdbc.service;
/**
 * Service layer
 * @author Eugene Kashitsyn
 */

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        String create = "CREATE TABLE person (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL , " +
                "name VARCHAR (20) NOT NULL , lastName VARCHAR (20) NOT NULL , age INT)";
        try {
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement(create);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS person";
        try {
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement(drop);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, int age) {
        String save = "INSERT INTO person (name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement(save);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(int id) {
        String remove = "DELETE FROM person WHERE id=" + id;
        try {
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement(remove);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = new Util().getConnection().createStatement().executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE person";
        try {
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement(clean);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

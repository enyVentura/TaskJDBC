package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        String create = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL , name VARCHAR (20) NOT NULL , lastName VARCHAR (20) NOT NULL , age INT)";
        try {
            PreparedStatement preparedStatement=new Util().getConnection().prepareStatement(create);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            System.out.println("Exception create");
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try {
            PreparedStatement preparedStatement=new Util().getConnection().prepareStatement(drop);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            System.out.println("Exception drop");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement=new Util().getConnection().prepareStatement(save);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            System.out.println("Exception insert");
        }
    }

    @Override
    public void removeUserById(long id) {
        String remove = "DELETE FROM users WHERE id=" + (int)id;
        try {
            PreparedStatement preparedStatement=new Util().getConnection().prepareStatement(remove);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            System.out.println("Exception remove");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = new Util().getConnection().createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), (byte) resultSet.getInt("age")));
            }
        } catch (SQLException throwables) {
            System.out.println("Exception getAllUsers");
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE users";
        try {
            PreparedStatement preparedStatement=new Util().getConnection().prepareStatement(clean);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            System.out.println("Exception clean");
        }
    }
}

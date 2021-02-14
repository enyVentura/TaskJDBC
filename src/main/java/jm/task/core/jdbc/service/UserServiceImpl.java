package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        String create = "CREATE TABLE users (id LONG PRIMARY KEY AUTO_INCREMENT NOT NULL , name VARCHAR (20) NOT NULL , lastName VARCHAR (20) NOT NULL , age BYTE)";
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(create);
        } catch (SQLException throwables) {
            System.out.println("Exception create");
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE [IF EXISTS ] users";
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(drop);
        } catch (SQLException throwables) {
            System.out.println("Exception drop");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT users(name, lastName, age) VALUES ("+name+", "+lastName+", "+age+")";
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(save);
        } catch (SQLException throwables) {
            System.out.println("Exception insert");
        }
    }

    @Override
    public void removeUserById(long id) {
        String remove = "DELETE FROM users WHERE id=" + id;
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(remove);
        } catch (SQLException throwables) {
            System.out.println("Exception remove");
        }
    }

    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = util.getConnection().createStatement().executeQuery("SELECT *FROM users");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
            }
        } catch (SQLException throwables) {
            System.out.println("Exception getAllUsers");
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE users";
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(clean);
        } catch (SQLException throwables) {
            System.out.println("Exception clean");
        }
    }
}

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Utils.getConnection();

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS triumf (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(15), lastname VARCHAR(15), age INT);";
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute(sql);
            System.out.println("Таблица успешно создана!");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка во время создания таблицы!");
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS triumf;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(drop);
            preparedStatement.execute(drop);
            System.out.println("Таблица удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка!");
        }
    }

    public void saveUser(String name, String lastname, byte age) {
        String sql = "INSERT INTO triumf (name, lastname, age) VALUES (" + "'" + name + "'," + "'" + lastname + "'," + age + ")";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute(sql);
            System.out.println("Пользователь добавлен");
        } catch (Exception e) {
            System.out.println("Ошибка!");
        }
    }

    public void removeUserById(long id) {
        String deleteUser = "DELETE FROM kata_db.triumf WHERE id = " + "'" + id + "'" + "LIMIT 1;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUser);
            preparedStatement.execute(deleteUser);
            System.out.println("Пользователь id которого = " + id + " Успешно удален.");
        } catch (Exception e) {
            System.out.println("Во время выполнения запроса произошла ошибка!");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM triumf;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);

            }
            System.out.println("Список пользователей: " + list);
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить запрос!");
        }
        return list;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE triumf;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(clean);
            preparedStatement.execute(clean);
            System.out.println("Таблица успешно очищена!");
        } catch (SQLException e) {
            System.out.println("Не удалось очистить таблицу!");
        }
    }
}


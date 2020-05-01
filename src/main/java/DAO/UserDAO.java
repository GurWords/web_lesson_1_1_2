package DAO;

import DBConfig.UsersJDBCUtil;
import IntarfaceDAO.InterfaceUserDAO;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements InterfaceUserDAO {
    public static UserDAO instance = null;

    private UserDAO(){}

    public static UserDAO getInstance() {
        if (instance == null){
            instance = new UserDAO();
        }
        return instance;
    }
    @Override
    public void deleteUser(User user) {
        try {
            Connection connection = UsersJDBCUtil.getInstance().getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where nameuser=? and age=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getAge());
            preparedStatement.execute();
            connection.close();
        } catch (Exception e){
            e.getStackTrace();
        }
    }

    @Override
    public List<User> selectAllUsers() {
        try {
            Connection connection = UsersJDBCUtil.getInstance().getMysqlConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            List<User> userList = new ArrayList<>();
            while (resultSet.next()){
                userList.add(new User
                        .Builder()
                        .withId(resultSet.getLong("id"))
                        .withName(resultSet.getString("nameuser"))
                        .withAge(resultSet.getLong("age"))
                        .build());
            }
            resultSet.close();
            return userList;
        } catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User oldUser,User newUser){
        Connection connection = UsersJDBCUtil.getInstance().getMysqlConnection();
        try {
            PreparedStatement preparedStatementone = connection.prepareStatement("update users set nameuser=? where nameuser like ?");
            preparedStatementone.setString(1,newUser.getName());
            preparedStatementone.setString(2,oldUser.getName());
            preparedStatementone.execute();
            PreparedStatement preparedStatementtwo = connection.prepareStatement("update users set age=? where age like ?");
            preparedStatementtwo.setLong(1,newUser.getAge());
            preparedStatementtwo.setLong(2,oldUser.getAge());
            preparedStatementtwo.execute();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public boolean insertUser(User user) {
        try {
            Connection connection = UsersJDBCUtil.getInstance().getMysqlConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (nameuser,age) VALUES (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getAge());
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rows == 1;
        } catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }

    @Override
    public void createTable() {
        try {
            Connection connection = UsersJDBCUtil.getInstance().getMysqlConnection();
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE users " +
                    "(id bigint auto_increment, nameuser varchar(256)" +
                    ",age bigint, primary key (id))");
            stmt.close();
            connection.close();
        } catch (Exception e){
            e.getStackTrace();
        }
    }
}

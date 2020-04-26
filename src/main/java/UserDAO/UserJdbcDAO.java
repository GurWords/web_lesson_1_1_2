package UserDAO;

import DAO.UserDAO;
import Model.User;
import com.mysql.jdbc.Driver;;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class UserJdbcDAO implements UserDAO {

    private static UserJdbcDAO instance = null;

    private UserJdbcDAO() {}

    public static UserJdbcDAO getInstance() {
        if (instance == null) {
            instance = new UserJdbcDAO();
        }
        return instance;
    }
    @Override
    public void deleteUser(User user) {
        try {
            user.setId(getIdByName(user.getName()));
            Connection connection = getMysqlConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setLong(1,user.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException s) {
            s.getStackTrace();
        }
    }
    @Override
    public String selectAllUsers() {
        try {
            Connection connection = getMysqlConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                stringBuilder.append("Name: ");
                stringBuilder.append(resultSet.getString("nameuser"));
                stringBuilder.append("Age: ");
                stringBuilder.append(resultSet.getLong("age"));
            }
            statement.close();
            connection.close();
            return stringBuilder.toString();
        } catch (SQLException sqlException) {
            sqlException.getStackTrace();
        }
        return null;
    }
    @Override
    public void updateUser(User newUser) {
        newUser.setId(getIdByName(newUser.getName()));
        Connection connection = getMysqlConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET nameuser=?,age=? where id=?");
            preparedStatement.setString(1,newUser.getName());
            preparedStatement.setLong(2,newUser.getAge());
            preparedStatement.setLong(3,newUser.getId());
            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insertUser(User user) {
        try {
            Connection connection = getMysqlConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (nameuser,age) VALUES (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getAge());
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public long getIdByName(String name) {
        Connection connection = getMysqlConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users where nameuser=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            connection.close();
            return id;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    private Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password
            return DriverManager.getConnection(url.toString());
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void createTable() throws Exception {
        Connection connection = getMysqlConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE users " +
                "(id bigint auto_increment, nameuser varchar(256)" +
                ",age bigint, primary key (id))");
        stmt.close();
        connection.close();
    }
}

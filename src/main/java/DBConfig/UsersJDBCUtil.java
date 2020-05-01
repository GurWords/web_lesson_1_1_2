package DBConfig;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UsersJDBCUtil {
    public static UsersJDBCUtil instance = null;

    private UsersJDBCUtil(){}

    public static UsersJDBCUtil getInstance() {
        if (instance == null){
            instance = new UsersJDBCUtil();
        }
        return instance;
    }

    public Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password
            return DriverManager.getConnection(url.toString());
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e ){
            e.printStackTrace();
        }
        return null;
    }
}

package ConfigDB;

import Model.User;
import com.mysql.jdbc.Driver;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper instance = null;
    private DBHelper(){}

    public static DBHelper getInstance() {
        if (instance == null){
            instance = new DBHelper();
        }
        return instance;
    }

    public Connection getConnection(){
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
    public Configuration getConfiguration(){
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "root");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            configuration.addAnnotatedClass(User.class);
            return configuration;
        } catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }
}

package ConfigDB;

import Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateConfig {
    private static HibernateConfig instance = null;
    private static SessionFactory sessionFactory = null;

    private HibernateConfig(){}

    public static HibernateConfig getInstance() {
        if(instance == null){
            instance = new HibernateConfig();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = getConfiguration();
        }
        return sessionFactory;
    }

    public SessionFactory getConfiguration(){
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "root");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");

            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }
    public Session getSession(){
        return getSessionFactory().openSession();
    }
}
package UserDAO;

import ConfigDB.HibernateConfig;
import DAO.UserDAO;
import Model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;


public class UserHibernateDAO implements UserDAO {

    private static UserHibernateDAO instance = null;

    private UserHibernateDAO(){}

    public static UserHibernateDAO getInstance() {
        if (instance == null){
            instance = new UserHibernateDAO();
        }
        return instance;
    }

    @Override
    public void deleteUser(User user) {
        user.setId(getIdByName(user.getName()));
        Session session = HibernateConfig.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public String selectAllUsers() {
        Session session = HibernateConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User").list();
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            stringBuilder.append(user.getName());
            stringBuilder.append(" ");
            stringBuilder.append(user.getAge());
        }
        session.close();
        return stringBuilder.toString();
    }

    @Override
    public void updateUser(User newUser) {
        Long id = getIdByName(newUser.getName());
        newUser.setId(id);
        Session session = HibernateConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User set name=:name,age=:age where id=:id");
        query.setParameter("name",newUser.getName());
        query.setParameter("age",newUser.getAge());
        query.setParameter("id",newUser.getId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void insertUser(User user) {
        Session session = HibernateConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public long getIdByName(String name) {
        Session session = HibernateConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User where name=:name");
        query.setParameter("name",name);
        Long id = query.uniqueResult().getId();
        transaction.commit();
        session.close();
        return id;
    }
}

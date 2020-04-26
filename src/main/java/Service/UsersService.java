package Service;

import DAO.UserDAO;
import UserDAO.*;
import Model.User;


public class UsersService {

    private static UsersService instance = null;

    private UsersService(){}

    public static UsersService getInstance() {
        if (instance == null){
            instance = new UsersService();
        }
        return instance;
    }

    public void updateUser(User newUser) {
        UserDAO userDAO = UserJdbcDAO.getInstance();
        userDAO.updateUser(newUser);
    }

    public String selectAllUsers() {
        UserDAO userDAO = UserJdbcDAO.getInstance();
        try {
            return userDAO.selectAllUsers();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public void createTable() {
        try {
            UserJdbcDAO.getInstance().createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertUser(User user) {
        UserDAO userDAO = UserJdbcDAO.getInstance();
        userDAO.insertUser(user);
    }

    public void deleteUser(User user) {
        UserDAO userDAO = UserJdbcDAO.getInstance();
        userDAO.deleteUser(user);
    }
}

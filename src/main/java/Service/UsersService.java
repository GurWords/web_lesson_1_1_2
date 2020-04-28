package Service;

import DAO.UserDAO;
import DAOFactory.UserDaoFactory;
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
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        userDAO.updateUser(newUser);
    }

    public String selectAllUsers() {
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        try {
            return userDAO.selectAllUsers();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public void insertUser(User user) {
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        userDAO.insertUser(user);
    }

    public void deleteUser(User user) {
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        userDAO.deleteUser(user);
    }
}

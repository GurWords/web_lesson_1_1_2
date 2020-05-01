package Service;

import DAO.UserDAO;
import InterfaceService.InterfaceUsersService;
import Model.User;

import java.util.List;

public class UsersService implements InterfaceUsersService {

    public static UsersService instance = null;

    private UsersService() {
    }

    public static UsersService getInstance() {
        if (instance == null) {
            instance = new UsersService();
        }
        return instance;
    }

    @Override
    public List<User> selectAllUsers() {
        return UserDAO.getInstance().selectAllUsers();
    }

    @Override
    public boolean insertUser(User user) {
        return UserDAO.getInstance().insertUser(user);
    }

    @Override
    public void deleteUser(User user) {
        UserDAO.getInstance().deleteUser(user);

    }

    @Override
    public void updateUser(User oldUser, User newUser) {
        UserDAO.getInstance().updateUser(oldUser, newUser);
    }
}

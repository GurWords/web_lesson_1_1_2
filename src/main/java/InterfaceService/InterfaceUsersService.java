package InterfaceService;

import Model.User;

import java.util.List;

public interface InterfaceUsersService {
    void deleteUser(User user);
    List<User> selectAllUsers();
    void updateUser(User oldUser,User newUser);
    boolean insertUser(User user);
}

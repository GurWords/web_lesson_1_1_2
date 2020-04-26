package DAO;

import Model.User;

public interface UserDAO {
    void deleteUser(User user);
    String selectAllUsers();
    void updateUser(User newUser);
    void insertUser(User user);
    long getIdByName(String name);
}

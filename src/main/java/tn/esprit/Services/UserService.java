package tn.esprit.Services;

import tn.esprit.Entities.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    User updateUser(User user);
    List<User> findAllUsers();
    User findUserById(Long id);
    void deleteUser (Long id);
}

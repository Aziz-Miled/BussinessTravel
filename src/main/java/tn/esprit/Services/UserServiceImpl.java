package tn.esprit.Services;


import tn.esprit.Exceptions.UserNotFoundException;
import tn.esprit.Entities.User;
import tn.esprit.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public User addUser(User user){
        return userRepo.save(user);
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User findUserById(Long id){
        return userRepo.findUserById(id).
                orElseThrow(() -> new UserNotFoundException("User avec l'id " + id + " non trouvé "));
    }

    public void deleteUser (Long id){
        userRepo.deleteUserById(id);
    }

}

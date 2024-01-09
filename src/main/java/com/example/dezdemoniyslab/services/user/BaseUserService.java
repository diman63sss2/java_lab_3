package com.example.dezdemoniyslab.services.user;

import com.example.dezdemoniyslab.models.Role;
import com.example.dezdemoniyslab.models.User;
import com.example.dezdemoniyslab.reposiotries.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseUserService {
    private final UserRepository userRepository;

    public void saveUserToDatabase(User user){
        userRepository.save(user);
    }

    public User getUserFromDatabaseById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsersFromDatabase(){
        return userRepository.findAll();
    }


    public void hardDeleteUserFromDatabaseById(Long userId){
        userRepository.deleteById(userId);
    }

    public void softDeleteUserFromDatabaseById(Long userId){
        User user = getUserFromDatabaseById(userId);
        user.setIsDeleted(true);
        saveUserToDatabase(user);
    }

}

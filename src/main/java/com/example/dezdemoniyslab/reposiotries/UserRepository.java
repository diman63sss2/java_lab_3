package com.example.dezdemoniyslab.reposiotries;


import com.example.dezdemoniyslab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndIsDeletedFalse(String Username);
}

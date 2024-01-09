package com.example.dezdemoniyslab;

import com.example.dezdemoniyslab.models.Role;
import com.example.dezdemoniyslab.models.User;
import com.example.dezdemoniyslab.requests.book.BookCreationRequest;
import com.example.dezdemoniyslab.requests.users.UserRegisterRequest;
import com.example.dezdemoniyslab.services.book.BookService;
import com.example.dezdemoniyslab.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class DezdemoniysLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(DezdemoniysLabApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            UserService userService,
            BookService bookService
    ) {
        return args -> {
            UserRegisterRequest admin = UserRegisterRequest.builder()
                    .firstname("admin")
                    .lastname("admin")
                    .username("admin")
                    .email("admin@admin.ru")
                    .password("admin")
                    .role(Role.ADMIN)
                    .build();
            userService.registerUser(admin);

            UserRegisterRequest user = UserRegisterRequest.builder()
                    .firstname("user")
                    .lastname("user")
                    .username("user")
                    .email("user@user.ru")
                    .password("user")
                    .role(Role.USER)
                    .build();
            userService.registerUser(user);


            BookCreationRequest bookCreationRequest1 = BookCreationRequest.builder()
                    .content("1")
                    .authorId(userService.getAllUsers().get(0).getId())
                    .build();
            bookService.createBook(bookCreationRequest1);

            BookCreationRequest bookCreationRequest2 = BookCreationRequest.builder()
                    .content("2")
                    .authorId(userService.getAllUsers().get(1).getId())
                    .build();
            bookService.createBook(bookCreationRequest2);


            BookCreationRequest bookCreationRequest3 = BookCreationRequest.builder()
                    .content("3")
                    .authorId(userService.getAllUsers().get(0).getId())
                    .build();
            bookService.createBook(bookCreationRequest3);
        };
    }

}

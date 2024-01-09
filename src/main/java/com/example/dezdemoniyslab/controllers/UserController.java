package com.example.dezdemoniyslab.controllers;

import com.example.dezdemoniyslab.models.User;
import com.example.dezdemoniyslab.requests.users.UserRegisterRequest;
import com.example.dezdemoniyslab.requests.users.UserUpdateRequest;
import com.example.dezdemoniyslab.services.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.List;

import static com.example.dezdemoniyslab.controllers.Mapper2XLT.transform2XLT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getUserList(@RequestHeader("Accept") String acceptHeader) {

        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(userService.getAllUsers(), "UserList.xslt")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    userService.getAllUsers()
            );
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId, @RequestHeader("Accept") String acceptHeader){
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(userService.getUserById(userId), "User.xslt")
            );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    userService.getUserById(userId)
            );
        }
    }



    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createUser(@RequestBody UserRegisterRequest userRegisterRequest){
        try{
            userService.registerUser(userRegisterRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.PATCH,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequest userUpdateRequest){
        try{
            userService.updateUser(userUpdateRequest, userId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User successfully updated");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete User
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> softDeleteUser(@PathVariable("userId") Long userId) {
        try{
            userService.deleteUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).body("User successfully deleted");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}


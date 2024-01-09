package com.example.dezdemoniyslab.controllers;


import com.example.dezdemoniyslab.models.Book;
import com.example.dezdemoniyslab.models.User;
import com.example.dezdemoniyslab.requests.book.BookCreationRequest;
import com.example.dezdemoniyslab.requests.book.BookUpdateRequest;
import com.example.dezdemoniyslab.requests.users.UserRegisterRequest;
import com.example.dezdemoniyslab.requests.users.UserUpdateRequest;
import com.example.dezdemoniyslab.services.book.BookService;
import com.example.dezdemoniyslab.services.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<?> getBookList(@RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(bookService.getAllBooks(), "BookList.xslt")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    bookService.getAllBooks()
            );
        }
    }

    @RequestMapping(value = "/books/author/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookListByAuthor(@PathVariable("userId") Long userId, @RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(bookService.getAllAuthorBooksByAuthorId(userId), "BookList.xslt")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    bookService.getAllAuthorBooksByAuthorId(userId)
            );
        }
    }
//
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookById(@PathVariable("bookId") Long bookId, @RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(bookService.getBookById(bookId), "Book.xslt")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    bookService.getBookById(bookId)
            );
        }
    }



    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createBook(@RequestBody BookCreationRequest bookCreationRequest){
        try{
            bookService.createBook(bookCreationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Book successfully created");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit/{bookId}", method = RequestMethod.PATCH,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateUser(@PathVariable("bookId") Long bookId, @RequestBody BookUpdateRequest bookUpdateRequest){
        try{
            bookService.updateBook(bookUpdateRequest, bookId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User successfully updated");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete User
    @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> softDeleteBook(@PathVariable("bookId") Long bookId) {
        try{
            bookService.deleteBookById(bookId);
            return ResponseEntity.status(HttpStatus.OK).body("Book successfully deleted");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}

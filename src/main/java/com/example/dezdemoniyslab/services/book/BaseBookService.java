package com.example.dezdemoniyslab.services.book;

import com.example.dezdemoniyslab.models.Book;
import com.example.dezdemoniyslab.models.User;
import com.example.dezdemoniyslab.reposiotries.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseBookService {
    private final BookRepository bookRepository;

    public void saveBookToDatabase(Book book){
        bookRepository.save(book);
    }

    public Book getBookFromDatabaseById(Long bookId){
        return bookRepository.findById(bookId).orElse(null);
    }

    public List<Book> getAllBooksFromDatabase(){
        return bookRepository.findAll();
    }


    public void hardDeleteBookFromDatabaseById(Long bookId){
        bookRepository.deleteById(bookId);
    }

    public void softDeleteBookFromDatabaseById(Book book){
        book.setIsDeleted(true);
        saveBookToDatabase(book);
    }
   public List<Book> getAllBooksFromDatabaseByAuthorId (Long userId){
        return bookRepository.findAllByUserId(userId);
   }

}

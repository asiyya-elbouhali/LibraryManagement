package com.mylibrary.dao;
import com.mylibrary.model.Book;

public interface BookDao {
    void addBook();
    int searchBookIdByISBN(String isbnToSearch);
    void deleteBookByISBN(String isbnToDelete);
    Book searchBookByISBN(String isbnToSearch);
    Book searchBookByTitle(String titleToSearch);
    Book searchBookByAuthor(String authorToSearch);
    Book searchBookByStatus(String statusToSearch);
    void showAllBooks();
    void borrowBookByISBN(String isbnToBorrow);
    void returnBookByISBN(String isbnToReturn);
    void updateBookInformationByISBN(String isbnToUpdate);
    void Menu();

}

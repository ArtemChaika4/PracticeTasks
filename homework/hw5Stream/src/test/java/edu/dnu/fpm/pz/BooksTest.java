package edu.dnu.fpm.pz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BooksTest {
    Books books;
    List<Book> bookList;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Before
    public void setUp() {
        books = new Books();
        bookList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            bookList.add(new Book("Title-" + i, "Author-" + i, "Isbn-" + i));
            bookList.add(new Book("Title-0" + i, "Author-" + i, "Isbn-0" + i));
        }

        for(Book book : bookList){
            books.add(book);
        }
    }

    @Test
    public void addNormalInitial() {
        //GIVEN
        int expectedSize = 5;
        int expectedNumberOfBooks = 2;
        //WHEN
        Map<String, Integer> map = books.getAuthorToNumberOfBooks();
        //THEN
        int actualSize = map.size();
        assertEquals(expectedSize, actualSize);
        for(int number : map.values()){
            assertEquals(expectedNumberOfBooks, number);
        }
    }

    @Test
    public void addNormal() {
        //GIVEN
        int expectedNumberOfBooks = 3;
        int expectedSize = 5;
        //WHEN
        books.add(new Book("Title-001", "Author-0", "Isbn-01"));
        Map<String, Integer> map = books.getAuthorToNumberOfBooks();
        //THEN
        int actualSize = map.size();
        int actualNumberOfBooks = map.get("Author-0");
        assertEquals(expectedSize, actualSize);
        assertEquals(expectedNumberOfBooks, actualNumberOfBooks);
    }
    @Test
    public void addContainedBook() {
        //GIVEN
        Book book = bookList.get(0);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Book already listed");
        //WHEN
        books.add(book);
    }

    @Test
    public void addNullBook() {
        //GIVEN
        Book book = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Book is null");
        //WHEN
        books.add(book);
    }

    @Test
    public void removeNormal() {
        //GIVEN
        Book book = bookList.get(1);
        int expectedSize = 5;
        int expectedNumberOfBooks = 1;
        //WHEN
        books.remove(book);
        Map<String, Integer> map = books.getAuthorToNumberOfBooks();
        //THEN
        int actualSize = map.size();
        int actualNumberOfBooks = map.get(book.getAuthor());
        assertEquals(expectedSize, actualSize);
        assertEquals(expectedNumberOfBooks, actualNumberOfBooks);
    }

    @Test
    public void removeAllAuthorBooks() {
        //GIVEN
        Book firstBook = bookList.get(0);
        Book secondBook = bookList.get(1);
        int expectedSize = 4;
        //WHEN
        books.remove(firstBook);
        books.remove(secondBook);
        Map<String, Integer> map = books.getAuthorToNumberOfBooks();
        //THEN
        int actualSize = map.size();
        assertEquals(expectedSize, actualSize);
    }

   @Test
   public void removeNotContainedBook(){
        //GIVEN
        Book book = new Book("title", "author", "isbn");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Book not found");
        //WHEN
        books.remove(book);
    }

    @Test
    public void removeNullBook(){
        //GIVEN
        Book book = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Book not found");
        //WHEN
        books.remove(book);
    }
}
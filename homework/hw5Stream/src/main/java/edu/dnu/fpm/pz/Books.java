package edu.dnu.fpm.pz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Books {
    private List<Book> books;
    private Map<String, Integer> authorToNumberOfBooks;

    public Books(){
        books = new ArrayList<>();
        authorToNumberOfBooks = new HashMap<>();
    }

    public void add(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book is null");
        }
        if(books.contains(book)){
            throw new IllegalArgumentException("Book already listed");
        }
        books.add(book);
        onListChanged();
    }

    public void remove(Book book){
        if(!books.remove(book)){
            throw new IllegalArgumentException("Book not found");
        }
        onListChanged();
    }

    private void onListChanged(){
        authorToNumberOfBooks = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingInt(x -> 1)));
    }

    public List<Book> getBooks() {
        return books;
    }

    public Map<String, Integer> getAuthorToNumberOfBooks() {
        return authorToNumberOfBooks;
    }
}

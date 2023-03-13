package com.yash.bookstore.service;

import java.util.List;

import com.yash.bookstore.entity.Book;

public interface BookService {

	Book saveBook(Book book);

	void deleteBook(Long id);

	List<Book> findAllBooks();

}

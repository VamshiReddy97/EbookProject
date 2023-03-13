package com.yash.bookstore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.bookstore.entity.Book;
import com.yash.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book saveBook(Book book) {

		book.setCreateTime(LocalDateTime.now());
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long id) {

		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();

	}

}

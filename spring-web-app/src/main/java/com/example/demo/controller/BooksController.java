package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;

@RestController
public class BooksController {
	@GetMapping("/books")
	public List<Book> getBooks(){
		return Arrays.asList(new Book(21,"To kill a Mocking Bird"));
	}
	
	@GetMapping("/")
	public String getRoot() {
		return "Hello";
	}
	
}

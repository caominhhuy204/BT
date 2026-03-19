package com.docker.caominhhuy.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book create(BookRequest request) {
        Book book = new Book(null, request.getTitle(), request.getAuthor(), request.getPublishedYear());
        return bookRepository.save(book);
    }

    public Book update(Long id, BookRequest request) {
        Book existing = bookRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setTitle(request.getTitle());
        existing.setAuthor(request.getAuthor());
        existing.setPublishedYear(request.getPublishedYear());
        return bookRepository.save(existing);
    }

    public boolean delete(Long id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }
}

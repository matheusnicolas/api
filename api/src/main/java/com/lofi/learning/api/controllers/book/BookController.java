package com.lofi.learning.api.controllers.book;


import com.lofi.learning.api.data.vo.v1.BookVO;
import com.lofi.learning.api.services.book.BookServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
public class BookController implements BookOperations {

    @Autowired
    private BookServicesImpl service;

    public BookVO create(BookVO book) {
        return service.create(book);
    }

    public BookVO update(BookVO book) {
        return service.update(book);
    }

    public List<BookVO> findAll() {
        return service.findAll();
    }

    public BookVO findById(Long id) {
        return service.findById(id);
    }

    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
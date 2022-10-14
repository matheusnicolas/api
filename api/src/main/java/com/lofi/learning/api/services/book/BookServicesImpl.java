package com.lofi.learning.api.services.book;

import java.util.List;
import java.util.logging.Logger;

import com.lofi.learning.api.controllers.book.BookController;
import com.lofi.learning.api.data.vo.v1.BookVO;
import com.lofi.learning.api.exceptions.RequiredObjectIsNullException;
import com.lofi.learning.api.exceptions.ResourceNotFoundException;
import com.lofi.learning.api.mapper.MapStructMapper;
import com.lofi.learning.api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

@Service
public class BookServicesImpl implements BookServices {

    private Logger logger = Logger.getLogger(BookServicesImpl.class.getName());

    @Autowired
    private BookRepository repository;

    @Autowired
    private MapStructMapper mapper;

    public BookVO create(BookVO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one book!");

        var bookEntity = mapper.bookVOToBookEntity(book);
        var bookVO = mapper.bookEntityToBookVO(repository.save(bookEntity));

        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    public BookVO update(BookVO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one book!");

        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No records found for the id [%s]", book.getKey())));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var bookVO = mapper.bookEntityToBookVO(repository.save(entity));
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    public List<BookVO> findAll() {

        logger.info("Finding all book!");

        var books = mapper.bookEntityListToBookVOList(repository.findAll());
        books
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));

        return books;
    }

    public BookVO findById(Long id) {

        logger.info("Finding one book!");

        var bookEntity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("No records found for the id [%s]", id)));

        var bookVO = mapper.bookEntityToBookVO(bookEntity);

        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    public void delete(Long id) {

        logger.info("Deleting one book!");

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}

package com.lofi.learning.api.services.book;

import com.lofi.learning.api.data.vo.v1.BookVO;

import java.util.List;

public interface BookServices {

    public BookVO create(BookVO book) throws Exception;

    public BookVO update(BookVO book) throws Exception;

    public List<BookVO> findAll() throws Exception;

    public BookVO findById(Long id) throws Exception;

    public void delete(Long id);
}

package com.lofi.learning.api.services;

import com.lofi.learning.api.data.vo.v1.PersonVO;

import java.util.List;

public interface PersonServices {

    public PersonVO create(PersonVO person) throws Exception;

    public void delete(Long id);

    public PersonVO update(PersonVO person) throws Exception;

    public List<PersonVO> findAll() throws Exception;

    public PersonVO findById(Long id) throws Exception;
}

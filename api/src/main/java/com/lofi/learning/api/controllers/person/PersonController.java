package com.lofi.learning.api.controllers.person;

import com.lofi.learning.api.data.vo.v1.PersonVO;
import com.lofi.learning.api.services.person.PersonServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController implements PersonOperations {

    @Autowired
    private PersonServicesImpl service;

    public PersonVO create(PersonVO person) throws Exception {
        return service.create(person);
    }

    public PersonVO update(PersonVO person) throws Exception {
        return service.update(person);
    }

    public List<PersonVO> findAll() throws Exception {
        return service.findAll();
    }

    public PersonVO findById(Long id) throws Exception {
        return service.findById(id);
    }

    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package com.lofi.learning.api.services;

import com.lofi.learning.api.exceptions.ResourceNotFoundException;
import com.lofi.learning.api.model.Person;
import com.lofi.learning.api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServicesImpl implements PersonServices {

    private Logger logger = Logger.getLogger(PersonServicesImpl.class.getName());

    @Autowired
    private PersonRepository repository;

    public Person create(Person person) {
        logger.info("Creating one person!");
        return repository.save(person);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for the id [%s]", id)));

        repository.delete(entity);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for the id [%s]", person.getId())));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public List<Person> findAll() {
        logger.info("Finding all people!");
        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for the id [%s]", id)));
    }
}

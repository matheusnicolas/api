package com.lofi.learning.api.services.person;

import com.lofi.learning.api.controllers.person.PersonController;
import com.lofi.learning.api.data.vo.v1.PersonVO;
import com.lofi.learning.api.exceptions.RequiredObjectIsNullException;
import com.lofi.learning.api.exceptions.ResourceNotFoundException;
import com.lofi.learning.api.mapper.MapStructMapper;
import com.lofi.learning.api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServicesImpl implements PersonServices {

    private final Logger logger = Logger.getLogger(PersonServicesImpl.class.getName());

    @Autowired
    private PersonRepository repository;

    @Autowired
    private MapStructMapper mapper;

    public PersonVO create(PersonVO person) throws Exception {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one person!");

        var personEntity = mapper.personVOToPersonModel(person);
        var personVO = mapper.personModelToPersonVO(repository.save(personEntity));

        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());

        return personVO;
    }


    public PersonVO update(PersonVO person) throws Exception {
        logger.info("Updating one person!");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for the id [%s]", person.getKey())));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var personVO = mapper.personModelToPersonVO(repository.save(entity));
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    public List<PersonVO> findAll() throws Exception {
        logger.info("Finding all people!");

        var personsVO = mapper.personModelListToPersonVOList(repository.findAll());

        personsVO.stream().forEach(person -> {
            try {
                person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
            } catch (Exception e) {
                logger.warning(String.format("An error occurred: [%s]", e.getMessage()));
            }
        });

        return personsVO;
    }

    public PersonVO findById(Long id) throws Exception {
        logger.info("Finding one person!");

        var personEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for the id [%s]", id)));

        var personVO = mapper.personModelToPersonVO(personEntity);

        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return personVO;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for the id [%s]", id)));

        repository.delete(entity);
    }
}

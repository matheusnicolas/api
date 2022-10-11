package com.lofi.learning.api.services;

import com.lofi.learning.api.controllers.PersonController;
import com.lofi.learning.api.data.vo.v1.PersonVO;
import com.lofi.learning.api.data.vo.v2.PersonVOV2;
import com.lofi.learning.api.exceptions.ResourceNotFoundException;
import com.lofi.learning.api.mapper.MapStructMapper;
import com.lofi.learning.api.mapper.custom.PersonMapper;
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

    @Autowired
    private PersonMapper personMapper;

    public PersonVO create(PersonVO person) throws Exception {
        logger.info("Creating one person!");

        var personEntity = mapper.personVOToPersonModel(person);
        var personVO = mapper.personModelToPersonVO(repository.save(personEntity));

        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());

        return personVO;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating one person with V2!");

        var personEntity = personMapper.convertVoTOEntity(person);
        var personVO2 = personMapper.convertEntityToVo(repository.save(personEntity));

        return personVO2;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for the id [%s]", id)));

        repository.delete(entity);
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

        var person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for the id [%s]", id)));

        PersonVO personVO = mapper.personModelToPersonVO(person);

        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return personVO;
    }
}

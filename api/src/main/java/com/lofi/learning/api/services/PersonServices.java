package com.lofi.learning.api.services;

import com.lofi.learning.api.model.Person;
import java.util.List;

public interface PersonServices {

    public Person create(Person person);

    public void delete(Long id);

    public Person update(Person person);

    public List<Person> findAll();

    public Person findById(Long id);
}

package com.lofi.learning.api.mapper;

import com.lofi.learning.api.data.vo.v1.PersonVO;
import com.lofi.learning.api.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    Person personVOToPersonModel(PersonVO personVO);

    PersonVO personModelToPersonVO(Person person);

    List<Person> personVOListToPersonModelList(List<PersonVO> personVOList);

    List<PersonVO> personModelListToPersonVOList(List<Person> personList);
}

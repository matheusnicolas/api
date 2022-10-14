package com.lofi.learning.api.mapper;

import com.lofi.learning.api.data.vo.v1.BookVO;
import com.lofi.learning.api.data.vo.v1.PersonVO;
import com.lofi.learning.api.model.Book;
import com.lofi.learning.api.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    @Mapping(target = "id", source = "key")
    Person personVOToPersonModel(PersonVO personVO);

    @Mapping(target = "key", source = "id")
    PersonVO personModelToPersonVO(Person person);

    List<Person> personVOListToPersonModelList(List<PersonVO> personVOList);

    List<PersonVO> personModelListToPersonVOList(List<Person> personList);

    @Mapping(target = "id", source = "key")
    Book bookVOToBookEntity(BookVO bookVO);

    @Mapping(target = "key", source = "id")
    BookVO bookEntityToBookVO(Book book);

    List<Book> bookVOListToBookEntityList(List<BookVO> personVOList);

    List<BookVO> bookEntityListToBookVOList(List<Book> personList);
}

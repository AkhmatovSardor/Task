package com.company.Task.mapper;

import com.company.Task.dto.BookDto;
import com.company.Task.entity.Book;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public abstract class BookMapper {
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract BookDto toDto(Book book);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract Book toEntity(BookDto dto);

    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Book book, BookDto value);
}

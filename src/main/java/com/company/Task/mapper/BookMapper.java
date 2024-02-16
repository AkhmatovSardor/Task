package com.company.Task.mapper;

import com.company.Task.dto.BookDto;
import com.company.Task.dto.Response;
import com.company.Task.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BookMapper {
    public abstract BookDto toDto(Book book);

    public abstract Book toEntity(BookDto dto);
}

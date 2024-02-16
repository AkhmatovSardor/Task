package com.company.Task.service;

import com.company.Task.dto.BookDto;
import com.company.Task.dto.MyCrud;
import com.company.Task.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreService implements MyCrud<Long, BookDto> {

    @Override
    public Response<BookDto> create(BookDto value) {
        return null;
    }

    @Override
    public Response<BookDto> get(Long key) {
        return null;
    }

    @Override
    public Response<BookDto> update(BookDto value, Long key) {
        return null;
    }

    @Override
    public Response<BookDto> delete(Long key) {
        return null;
    }

    public Response<List<BookDto>> getAll() {
        return null;
    }
}
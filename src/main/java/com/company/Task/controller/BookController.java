package com.company.Task.controller;

import com.company.Task.dto.BookDto;
import com.company.Task.dto.MyCrud;
import com.company.Task.dto.Response;
import com.company.Task.service.BookStoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")

public class BookController implements MyCrud<Long, BookDto> {
    @Autowired
    private BookStoreService bookStoreService;

    @PostMapping("/create")
    @Override
    public Response<BookDto> create(@Valid @RequestBody BookDto value) {
        return bookStoreService.create(value);
    }

    @GetMapping("/get/{id}")
    @Override
    public Response<BookDto> get(@PathVariable(value = "id") Long key) {
        return bookStoreService.get(key);
    }

    @PutMapping("/update/{id}")
    @Override
    public Response<BookDto> update(@RequestBody BookDto value, @PathVariable(value = "id") Long key) {
        return bookStoreService.update(value, key);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public Response<BookDto> delete(@PathVariable(value = "id") Long key) {
        return delete(key);
    }

    @GetMapping("/get-all")
    public Response<List<BookDto>> getAll() {
        return bookStoreService.getAll();
    }

}
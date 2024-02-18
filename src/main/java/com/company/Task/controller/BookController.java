package com.company.Task.controller;

import com.company.Task.dto.BookDto;
import com.company.Task.dto.Crud;
import com.company.Task.dto.Response;
import com.company.Task.service.BookStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")

public class BookController implements Crud<Long, BookDto> {
    @Autowired
    private BookStoreService bookStoreService;

    @Operation(tags = "Create", summary = "Book-ni yaratadi tipo qo'shadi!",
            responses = @ApiResponse(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookDto.class))))
    @PostMapping("/create")
    @Override
    public Response<BookDto> create(@Valid @RequestBody BookDto value) {
        return bookStoreService.create(value);

    }

    @Operation(tags = "Get", summary = "Book-ni get qiladi idsi orqali!")
    @GetMapping("/get/{id}")
    @Override
    public Response<BookDto> get(@PathVariable(value = "id") Long key) {
        return bookStoreService.get(key);
    }

    @Operation(tags = "Update", summary = "Boock-ni update qiladi idsi orqali",
            description = "Bu metod Book ni elementi buyicha yangilaydi!, kerak bo'lishi mumkin bo'lgan joyi pricini yangilash kerak bo'lishi mumkin!",
            responses = @ApiResponse(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookDto.class))))
    @PutMapping("/update/{id}")
    @Override
    public Response<BookDto> update(@RequestBody BookDto value, @PathVariable(value = "id") Long key) {
        return bookStoreService.update(value, key);
    }

    @Operation(tags = "Delete", summary = "Book-ni delete qiladi idsi orqali!")
    @DeleteMapping("/delete/{id}")
    @Override
    public Response<BookDto> delete(@PathVariable(value = "id") Long key) {
        return bookStoreService.delete(key);
    }

    @Operation(tags = "Get", summary = "Book-larni get qiladi!")
    @GetMapping("/get-all")
    public Response<List<BookDto>> getAll() {
        return bookStoreService.getAll();
    }

}
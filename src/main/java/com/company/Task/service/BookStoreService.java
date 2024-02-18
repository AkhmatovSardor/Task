package com.company.Task.service;

import com.company.Task.dto.BookDto;
import com.company.Task.dto.Crud;
import com.company.Task.dto.Response;
import com.company.Task.entity.Book;
import com.company.Task.exception.LogicException;
import com.company.Task.exception.ServiceException;
import com.company.Task.mapper.BookMapper;
import com.company.Task.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookStoreService implements Crud<Long, BookDto> {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @SneakyThrows
    @Override
    public Response<BookDto> create(BookDto value) {
        return Response.<BookDto>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Book successful created||add!")
                .data(bookMapper.toDto(bookRepository.save(bookMapper.toEntity(value))))
                .build();
    }

    @Override
    public Response<BookDto> get(Long key) {
        return bookRepository.findByBookIdAndDeletedAtIsNull(key).map(book ->
                Response.<BookDto>builder()
                        .success(true)
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .data(bookMapper.toDto(book))
                        .build()
        ).orElse(Response.<BookDto>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(String.format("With %d id book not found!", key))
                .build());
    }

    @Override
    public Response<BookDto> update(BookDto value, Long key) {
        try {
            return bookRepository.findByBookIdAndDeletedAtIsNull(key)
                    .map(book -> {
                        bookMapper.update(book, value);
                        bookRepository.save(book);
                        return Response.<BookDto>builder()
                                .success(true)
                                .code(HttpStatus.OK.value())
                                .message("Book successful updated!")
                                .data(bookMapper.toDto(book))
                                .build();
                    }).orElse(Response.<BookDto>builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .message(String.format("With %d id book not found!", key))
                            .build());
        } catch (ServiceException e) {
            throw new ServiceException(Response.<BookDto>builder()
                    .message(String.format("While updating error %s", e.getMessage()))
                    .code(HttpStatus.EXPECTATION_FAILED.value())
                    .build());
        }
    }

    @Override
    public Response<BookDto> delete(Long key) {
        try {
            return bookRepository.findByBookIdAndDeletedAtIsNull(key)
                    .map(book -> {
                        book.setDeletedAt(LocalDateTime.now());
                        bookRepository.save(book);
                        return Response.<BookDto>builder()
                                .success(true)
                                .code(HttpStatus.OK.value())
                                .message("Book successful deleted!")
                                .data(bookMapper.toDto(book))
                                .build();
                    }).orElse(Response.<BookDto>builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .message(String.format("With %d id book not found!", key))
                            .build());
        } catch (ServiceException e) {
            throw new ServiceException(Response.<BookDto>builder()
                    .message(String.format("While deleting error %s", e.getMessage()))
                    .code(HttpStatus.EXPECTATION_FAILED.value())
                    .build());
        }
    }

    public Response<List<BookDto>> getAll() {
        return Response.<List<BookDto>>builder()
                .message("Successfully")
                .code(HttpStatus.OK.value())
                .success(true)
                .data(bookRepository.findAllByDeletedAtIsNull().stream().map(bookMapper::toDto).toList())
                .build();
    }

    @SneakyThrows
    @Transactional
    public Response<List<BookDto>> getAllById(List<Long> id) {
        List<BookDto> myBook = new LinkedList<>();

        for (Long aLong : id) {
            Optional<Book> byBookIdAndDeletedAtIsNull = bookRepository.findByBookIdAndDeletedAtIsNull(aLong);
            if (byBookIdAndDeletedAtIsNull.isEmpty()) {
                throw new LogicException("Book not found with id: " + aLong);
            } else {
                if (byBookIdAndDeletedAtIsNull.get().getQuantityInStock() < 1) {
                    throw new LogicException("There are no books of this type left on sale: " + aLong);
                } else {
                    byBookIdAndDeletedAtIsNull.get().setQuantityInStock(byBookIdAndDeletedAtIsNull.get().getQuantityInStock() - 1);
                    bookRepository.save(byBookIdAndDeletedAtIsNull.get());
                }
                myBook.add(bookMapper.toDto(byBookIdAndDeletedAtIsNull.get()));
            }
        }
        return Response.<List<BookDto>>builder()
                .message("Successfully")
                .code(HttpStatus.OK.value())
                .success(true)
                .data(myBook)
                .build();
    }

    @SneakyThrows
    public Response<List<Book>> getAllByIds(List<Long> id) {
        List<Book> myBook = new LinkedList<>();
        for (Long aLong : id) {
            Optional<Book> byBookIdAndDeletedAtIsNull = bookRepository.findByBookIdAndDeletedAtIsNull(aLong);
            if (byBookIdAndDeletedAtIsNull.isEmpty()) {
                throw new LogicException("Book not found with id: " + aLong);
            } else {
                myBook.add(byBookIdAndDeletedAtIsNull.get());
            }
        }
        return Response.<List<Book>>builder()
                .message("Successfully")
                .code(HttpStatus.OK.value())
                .success(true)
                .data(myBook)
                .build();
    }
}
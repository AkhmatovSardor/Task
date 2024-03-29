package com.company.Task.repository;

import com.company.Task.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByBookIdAndDeletedAtIsNull(Long id);

    List<Book> findAllByDeletedAtIsNull();
}
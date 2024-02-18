package com.company.Task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    Long bookId;
    String title;
    @NotBlank(message = "author cannot be null or blank!")
    String author;
    @NotBlank(message = "genre cannot be null or blank!")
    String genre;
    @NotNull(message = "price cannot be null or blank!")
    Double price;
    Integer quantityInStock;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}

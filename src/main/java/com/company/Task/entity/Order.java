package com.company.Task.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = ("orders"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @ManyToOne
    @JoinColumn(name = ("customerId"), nullable = false)
    Customer customer;

    @ManyToMany
    @JoinTable(name = ("order_books"),
            joinColumns = @JoinColumn(name = ("orderId")),
            inverseJoinColumns = @JoinColumn(name = ("bookId")))
    private List<Book> books;

    LocalDateTime orderData;

    LocalDateTime createdAt;
}

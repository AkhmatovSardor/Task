package com.company.Task.service;

import com.company.Task.dto.OrderDto;
import com.company.Task.dto.OrderRequest;
import com.company.Task.dto.Response;
import com.company.Task.entity.Book;
import com.company.Task.exception.LogicException;
import com.company.Task.exception.ServiceException;
import com.company.Task.mapper.OrderMapper;
import com.company.Task.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerService customerService;
    private final BookStoreService bookStoreService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Response<OrderDto> create(OrderRequest value) {
        try {
            return Response.<OrderDto>builder()
                    .success(true)
                    .message("Order created successfully!")
                    .code(HttpStatus.OK.value())
                    .data(orderMapper.toDto(orderRepository.save(orderMapper.toEntity(OrderDto.builder()
                            .customer(customerService.custom(value))
                            .books(bookStoreService.getAllById(value.getBookIds()).getData())
                            .orderData(LocalDateTime.now())
                            .build()))))
                    .build();
        } catch (ServiceException e) {
            throw new ServiceException(Response.<OrderDto>builder()
                    .message(String.format("While saving error %s", e.getMessage()))
                    .code(HttpStatus.EXPECTATION_FAILED.value())
                    .build());
        }
    }

    @SneakyThrows
    public Response<List<OrderDto>> getOrderByCustomerId(Long key) {
        List<OrderDto> list = orderRepository.findAllByCustomer_CustomerId_AndCustomer_DeletedAtIsNull(key).stream().map(orderMapper::toDto).toList();
        if (list.isEmpty()) {
            throw new LogicException("The client has not yet placed such an order");
        } else {
            return Response.<List<OrderDto>>builder()
                    .success(true)
                    .message("Success")
                    .data(list)
                    .code(HttpStatus.OK.value())
                    .build();
        }
    }

    public Double sumByOrders(List<Long> xc) {
        double sum = 0;
        for (Book b : bookStoreService.getAllByIds(xc).getData()) {
            sum += b.getPrice();
        }
        return sum;
    }
}
package com.company.Task.service;

import com.company.Task.dto.Crud;
import com.company.Task.dto.CustomerDto;
import com.company.Task.dto.OrderRequest;
import com.company.Task.dto.Response;
import com.company.Task.exception.LogicException;
import com.company.Task.exception.ServiceException;
import com.company.Task.mapper.CustomerMapper;
import com.company.Task.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService implements Crud<Long, CustomerDto> {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public Response<CustomerDto> create(CustomerDto value) {
        try {
            return Response.<CustomerDto>builder()
                    .code(HttpStatus.OK.value())
                    .success(true)
                    .message("Customer successful created!")
                    .data(customerMapper.toDto(customerRepository.save(customerMapper.toEntity(value))))
                    .build();
        } catch (Exception e) {
            throw new ServiceException(Response.<CustomerDto>builder()
                    .message(String.format("While saving error %s", e.getMessage()))
                    .code(HttpStatus.EXPECTATION_FAILED.value())
                    .build());
        }
    }

    @SneakyThrows
    @Override
    public Response<CustomerDto> get(Long key) {
        return customerRepository.findByCustomerIdAndDeletedAtIsNull(key)
                .map(customer -> Response.<CustomerDto>builder()
                        .success(true)
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .data(customerMapper.toDto(customer))
                        .build())
                .orElse(Response.<CustomerDto>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(String.format("With %d id customer not found!", key))
                        .build());
    }

    @Override
    public Response<CustomerDto> update(CustomerDto value, Long key) {
        try {
            return customerRepository.findByCustomerIdAndDeletedAtIsNull(key)
                    .map(customer -> {
                        customerMapper.update(customer, value);
                        customerRepository.save(customer);
                        return Response.<CustomerDto>builder()
                                .message("Customer successful updated!")
                                .code(HttpStatus.OK.value())
                                .success(true)
                                .data(customerMapper.toDto(customer))
                                .build();
                    })
                    .orElse(Response.<CustomerDto>builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .message(String.format("With %d id customer not found!", key))
                            .build());
        } catch (Exception e) {
            return Response.<CustomerDto>builder()
                    .message(String.format("While updating error %s", e.getMessage()))
                    .code(HttpStatus.EXPECTATION_FAILED.value())
                    .build();
        }
    }

    @Override
    public Response<CustomerDto> delete(Long key) {
        try {
            return customerRepository.findByCustomerIdAndDeletedAtIsNull(key)
                    .map(customer -> {
                        customer.setDeletedAt(LocalDateTime.now());
                        customerRepository.save(customer);
                        return Response.<CustomerDto>builder()
                                .message("Customer successful deleted!")
                                .code(HttpStatus.OK.value())
                                .success(true)
                                .data(customerMapper.toDto(customer))
                                .build();
                    })
                    .orElse(Response.<CustomerDto>builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .message(String.format("With %d id customer not found!", key))
                            .build());
        } catch (Exception e) {
            throw new ServiceException(Response.<CustomerDto>builder()
                    .message(String.format("While deleting error %s", e.getMessage()))
                    .code(HttpStatus.EXPECTATION_FAILED.value())
                    .build());
        }
    }

    @SneakyThrows
    public CustomerDto custom(OrderRequest value) {
        if (get(value.getCustomerId()).getData() != null) {
            return customerMapper.toDto(customerRepository.save(
                    customerMapper.toEntityForOrder(get(value.getCustomerId()).getData(), value.getBookIds()))
            );
        } else {
            throw new LogicException("Customer not found with id " + value.getCustomerId());
        }
    }
}
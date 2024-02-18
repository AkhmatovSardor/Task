package com.company.Task.controller;

import com.company.Task.dto.CustomerDto;
import com.company.Task.dto.Crud;
import com.company.Task.dto.Response;
import com.company.Task.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController implements Crud<Long, CustomerDto> {
    @Autowired
    private CustomerService customerService;

    @Operation(tags = "Create", summary = "Customer-ni yaratadi!",
            description = "Web-sitga yangi mijoz registratsiyadan utishi,yangi customer!",
            responses = @ApiResponse(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerDto.class))))
    @PostMapping("/create")
    @Override
    public Response<CustomerDto> create(@Valid @RequestBody CustomerDto value) {
        return customerService.create(value);
    }

    @Operation(tags = "Get", summary = "Customer-ni get qiladi idsi orqali!")
    @GetMapping("/get/{id}")
    @Override
    public Response<CustomerDto> get(@PathVariable(value = "id") Long key) {
        return customerService.get(key);
    }

    @Operation(tags = "Update", summary = "Customer-ni update qiladi idsi orqali",
            description = "Bu metod Customer elementi buyicha yangilaydi!",
            responses = @ApiResponse(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerDto.class))))
    @PutMapping("/update/{id}")
    @Override
    public Response<CustomerDto> update(@RequestBody CustomerDto value, @PathVariable(value = "id") Long key) {
        return customerService.update(value, key);
    }

    @Operation(tags = "Delete", summary = "Customer-ni delete qiladi idsi orqali!")
    @DeleteMapping("/delete/{id}")
    @Override
    public Response<CustomerDto> delete(@PathVariable(value = "id") Long key) {
        return customerService.delete(key);
    }
}
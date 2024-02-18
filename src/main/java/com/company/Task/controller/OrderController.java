package com.company.Task.controller;

import com.company.Task.dto.OrderDto;
import com.company.Task.dto.OrderRequest;
import com.company.Task.dto.Response;
import com.company.Task.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(tags = "Create", summary = "Order-ni yaratadi!",
            responses = @ApiResponse(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderRequest.class))))
    @PostMapping("/create")
    public Response<OrderDto> create(@Valid @RequestBody OrderRequest value) {
        return orderService.create(value);
    }

    @Operation(tags = "Get", summary = "Order-ni get qiladi cud=stomer id orqali,customerga tegishlik bo'lgan orderlar!")
    @GetMapping("/get-orders-by-customer/{id}")
    public Response<List<OrderDto>> getOrderByCustomerId(@PathVariable(value = "id") Long key) {
        return orderService.getOrderByCustomerId(key);
    }
}
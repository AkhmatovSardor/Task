package com.company.Task.mapper;

import com.company.Task.dto.CustomerDto;
import com.company.Task.entity.Customer;
import com.company.Task.service.OrderService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public abstract class CustomerMapper {
    @Lazy
    @Autowired
    protected OrderService orderService;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract CustomerDto toDto(Customer customer);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "totalPurchasesAmount", expression = "java(0.0)")
    public abstract Customer toEntity(CustomerDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "totalPurchasesAmount", expression = "java(dto.getTotalPurchasesAmount() + orderService.sumByOrders(ids))")
    public abstract Customer toEntityForOrder(CustomerDto dto, List<Long> ids);

    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Customer customer, CustomerDto value);
}

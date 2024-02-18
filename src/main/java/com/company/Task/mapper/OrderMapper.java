package com.company.Task.mapper;

import com.company.Task.dto.OrderDto;
import com.company.Task.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public abstract class OrderMapper {
    @Mapping(target = "createdAt", ignore = true)
    public abstract OrderDto toDto(Order order);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    public abstract Order toEntity(OrderDto order);

}
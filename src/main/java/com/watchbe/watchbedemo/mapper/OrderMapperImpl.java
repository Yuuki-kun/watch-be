package com.watchbe.watchbedemo.mapper;

import com.watchbe.watchbedemo.dto.OrderDetailsDto;
import com.watchbe.watchbedemo.dto.OrderDto;
import com.watchbe.watchbedemo.model.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements MapperDto<Order, OrderDto>{
    private final ModelMapper modelMapper;


    @Override
    public OrderDto mapTo(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Order mapFrom(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}

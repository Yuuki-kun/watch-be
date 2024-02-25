package com.watchbe.watchbedemo.mapper;

import com.watchbe.watchbedemo.dto.OrderDetailsDto;
import com.watchbe.watchbedemo.model.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDetailsMapperImpl implements MapperDto<OrderDetails, OrderDetailsDto> {
    private final ModelMapper modelMapper;

    @Override
    public OrderDetailsDto mapTo(OrderDetails orderDetails) {
       return modelMapper.map(orderDetails, OrderDetailsDto.class);
    }

    @Override
    public OrderDetails mapFrom(OrderDetailsDto orderDetailsDto) {
        return modelMapper.map(orderDetailsDto, OrderDetails.class);
    }
}

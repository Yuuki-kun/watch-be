package com.securityjwt.demojwt.mapper;

import com.securityjwt.demojwt.dto.OrderDetailsDto;
import com.securityjwt.demojwt.dto.WatchDto;
import com.securityjwt.demojwt.model.OrderDetails;
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

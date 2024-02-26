package com.watchbe.watchbedemo.mapper;

import com.watchbe.watchbedemo.dto.FavoriteDto;
import com.watchbe.watchbedemo.dto.OrderDetailsDto;
import com.watchbe.watchbedemo.model.Favorite;
import com.watchbe.watchbedemo.model.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteMapperImpl implements MapperDto<Favorite, FavoriteDto>{
    private final ModelMapper modelMapper;
    @Override
    public FavoriteDto mapTo(Favorite favorite) {
        return modelMapper.map(favorite, FavoriteDto.class);
    }

    @Override
    public Favorite mapFrom(FavoriteDto favoriteDto) {
        return modelMapper.map(favoriteDto, Favorite.class);
    }
}

package com.watchbe.watchbedemo.mapper;

import com.watchbe.watchbedemo.dto.WatchDto;
import com.watchbe.watchbedemo.model.Watch;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WatchMapperImpl implements MapperDto<Watch, WatchDto>{

    private final ModelMapper modelMapper;

    @Override
    public WatchDto mapTo(Watch watch) {
        return modelMapper.map(watch, WatchDto.class);
    }

    @Override
    public Watch mapFrom(WatchDto watchDto) {
        return modelMapper.map(watchDto, Watch.class);
    }
}

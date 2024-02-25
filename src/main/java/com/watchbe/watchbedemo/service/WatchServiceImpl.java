package com.watchbe.watchbedemo.service;

import com.watchbe.watchbedemo.dto.WatchDto;
import com.watchbe.watchbedemo.exception.NotFoundException;
import com.watchbe.watchbedemo.mapper.WatchMapperImpl;
import com.watchbe.watchbedemo.model.Watch;
import com.watchbe.watchbedemo.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchServiceImpl implements WatchService {
    private final WatchRepository watchRepository;
    private final WatchMapperImpl watchMapper;

    @Override
    public List<WatchDto> getAll() {
        List<Watch> watches = watchRepository.findAll();
//        System.out.println("images-"+watches.get(0).getImages());
        return watches.stream().map(watchMapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public WatchDto findWatchByReference(String reference) {
        Watch watch =
                watchRepository.findWatchByReference(reference)
                        .orElseThrow(() -> new NotFoundException(String.format("Watch with reference %s not found!",
                                reference)));

        return watchMapper.mapTo(watch);
    }


}

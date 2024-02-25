package com.securityjwt.demojwt.service;

import com.securityjwt.demojwt.dto.WatchDto;
import com.securityjwt.demojwt.exception.NotFoundException;
import com.securityjwt.demojwt.mapper.WatchMapperImpl;
import com.securityjwt.demojwt.model.Watch;
import com.securityjwt.demojwt.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

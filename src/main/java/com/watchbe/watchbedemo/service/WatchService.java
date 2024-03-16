package com.watchbe.watchbedemo.service;

import com.watchbe.watchbedemo.dto.WatchDto;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchService {
    List<WatchDto> getAll();
    WatchDto findWatchByReference(String reference);
    List<WatchDto> getAll(Pageable page);
}

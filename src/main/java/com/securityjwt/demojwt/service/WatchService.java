package com.securityjwt.demojwt.service;

import com.securityjwt.demojwt.dto.WatchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchService {
    List<WatchDto> getAll();
    WatchDto findWatchByReference(String reference);
}

package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.dto.WatchDto;
import com.watchbe.watchbedemo.repository.ReviewRepository;
import com.watchbe.watchbedemo.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class WatchController {
    /*services*/
    private final WatchService watchService;
    private final ReviewRepository reviewRepository;
    @GetMapping
    public ResponseEntity<List<WatchDto>> getAllWatch(){
        return ResponseEntity.ok(watchService.getAll());
    }

    @GetMapping("/page")
    public ResponseEntity<List<WatchDto>> getAllWatchByPage(Pageable page){
        List<WatchDto> watchDtos = watchService.getAll(page);
        List<WatchDto> doubleList =new ArrayList<>();
        for(int i=0; i<10; i++){
            watchDtos.forEach(watchDto -> doubleList.add(watchDto));
        }
        return ResponseEntity.ok(doubleList);
    }
    
    @GetMapping("/details/{reference}")
    public ResponseEntity<WatchDto> getWatchDetailsByReference(@PathVariable String reference){
        System.out.println(reference);
        //try to add new reply to review
//        Review r = reviewRepository.findById(1L).orElseThrow();
//                    Review reply1 = Review.builder()
//                    .comment("reply first comment")
//                    .build();
//            r.addChildReview(reply1);

        return new ResponseEntity<>(watchService.findWatchByReference(reference), HttpStatus.OK);
    }
}

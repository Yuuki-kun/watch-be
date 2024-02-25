package com.securityjwt.demojwt.controller;

import com.securityjwt.demojwt.dto.WatchDto;
import com.securityjwt.demojwt.model.Image;
import com.securityjwt.demojwt.model.Review;
import com.securityjwt.demojwt.repository.ReviewRepository;
import com.securityjwt.demojwt.repository.WatchRepository;
import com.securityjwt.demojwt.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

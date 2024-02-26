package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.dto.FavoriteDto;
import com.watchbe.watchbedemo.dto.ShortFavoriteDto;
import com.watchbe.watchbedemo.exception.NotFoundException;
import com.watchbe.watchbedemo.mapper.FavoriteMapperImpl;
import com.watchbe.watchbedemo.model.Customer;
import com.watchbe.watchbedemo.model.Favorite;
import com.watchbe.watchbedemo.model.account.Account;
import com.watchbe.watchbedemo.repository.AccountRepository;
import com.watchbe.watchbedemo.repository.CustomerRepository;
import com.watchbe.watchbedemo.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapperImpl favoriteMapper;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    @GetMapping
    public ResponseEntity<?> getFavoriteList(@RequestParam("email") String customerEmail) {
        try {
            Account account = accountRepository.findByEmail(customerEmail).orElseThrow(()-> new NotFoundException(
                    "email "+customerEmail +"not found."));
            Customer customer = customerRepository.findCustomerByAccountId(account.getId());
            List<Favorite> favorites = favoriteRepository.findFavoritesByCustomerId(customer.getId());
            List<ShortFavoriteDto> shortFavoriteDtos = favorites.stream()
                    .map(favoriteMapper::mapTo)
                    .map(favoriteDto -> ShortFavoriteDto.builder()
                            .id(favoriteDto.getId())
                            .date(favoriteDto.getDateAdded())
                            .watchId(favoriteDto.getWatch().getId())
                            .build())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(shortFavoriteDtos);
        } catch (NumberFormatException e) {
            String errorMessage = "Invalid customerId. Please provide a valid customerId.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList(errorMessage));
        } catch (Exception e) {
            String errorMessage = "An error occurred while processing your request.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(errorMessage));
        }
    }
}

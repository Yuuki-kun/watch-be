package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.dto.FavoriteDto;
import com.watchbe.watchbedemo.dto.ShortFavoriteDto;
import com.watchbe.watchbedemo.exception.NotFoundException;
import com.watchbe.watchbedemo.mapper.FavoriteMapperImpl;
import com.watchbe.watchbedemo.model.Customer;
import com.watchbe.watchbedemo.model.Favorite;
import com.watchbe.watchbedemo.model.Watch;
import com.watchbe.watchbedemo.model.account.Account;
import com.watchbe.watchbedemo.repository.AccountRepository;
import com.watchbe.watchbedemo.repository.CustomerRepository;
import com.watchbe.watchbedemo.repository.FavoriteRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapperImpl favoriteMapper;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final EntityManager entityManager;

    @GetMapping
    public ResponseEntity<?> getFavoriteList(@RequestParam("id") String customerId) {
        try {
//            Account account = accountRepository.findByEmail(customerEmail).orElseThrow(()-> new NotFoundException(
//                    "email "+customerEmail +"not found."));
//            Customer customer =
//                    customerRepository.findById(Long.valueOf(customerId)).orElseThrow(()-> new NotFoundException(
//                            "customer with id "+customerId+" not found"));
            List<Favorite> favorites = favoriteRepository.findFavoritesByCustomerId(Long.valueOf(customerId));
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

    @PostMapping("/add-to-favorite-list/{id}/{watchId}")
    public ResponseEntity<?> addToFavoriteList(@PathVariable("id") Long customerId,
                                               @PathVariable("watchId") Long watchId){
        Optional<Customer> customer = customerRepository.findById(customerId);
       try{
           Watch watch = entityManager.getReference(Watch.class, watchId);
           Favorite favorite = Favorite.builder().customer(customer.get()).watch(watch).dateAdded(new Date()).build();
           favoriteRepository.save(favorite);
           List<Favorite> favorites = favoriteRepository.findFavoritesByCustomerId(customer.get().getId());
           List<ShortFavoriteDto> shortFavoriteDtos = favorites.stream()
                   .map(favoriteMapper::mapTo)
                   .map(favoriteDto -> ShortFavoriteDto.builder()
                           .id(favoriteDto.getId())
                           .date(favoriteDto.getDateAdded())
                           .watchId(favoriteDto.getWatch().getId())
                           .build())
                   .collect(Collectors.toList());
           return ResponseEntity.ok(shortFavoriteDtos);
       }catch (NotFoundException ex){
           throw new NotFoundException("customer with id "+customerId+" not fond!");
       }
    }

//    @Transactional
    @DeleteMapping("/{cid}/{wid}")
    public ResponseEntity<?> removeFromFavoriteList(@PathVariable("cid") Long cid, @PathVariable("wid")Long wid){
      try{
          favoriteRepository.deleteFavoriteByCustomerIdAndWatchId(cid, wid);
          return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);

      }catch(Exception e){
          return new ResponseEntity<>("remove error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     *
     * @param cid: id of customer
     * @return
     */
    @GetMapping("/list-detail/{cid}")
    private List<FavoriteDto> getFavoriteListDetails(@PathVariable long cid){
        return favoriteRepository.findAllByCustomerId(cid).stream().map(favoriteMapper::mapTo).collect(Collectors.toList());
    }
}

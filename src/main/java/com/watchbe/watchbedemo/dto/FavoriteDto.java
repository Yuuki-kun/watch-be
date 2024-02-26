package com.watchbe.watchbedemo.dto;

import com.watchbe.watchbedemo.model.Customer;
import com.watchbe.watchbedemo.model.Watch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {
    private Long id;
    private Date dateAdded;
    private WatchDto watch;
//    private CustomerDto customer;
}

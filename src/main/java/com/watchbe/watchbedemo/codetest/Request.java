package com.watchbe.watchbedemo.codetest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {

   private long amount;

   private String email;

   private String productName;
}

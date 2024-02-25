package com.watchbe.watchbedemo.mapper;

public interface MapperDto<A, B> {
    B mapTo(A a);
    A mapFrom(B b);
}

package com.securityjwt.demojwt.mapper;

public interface MapperDto<A, B> {
    B mapTo(A a);
    A mapFrom(B b);
}

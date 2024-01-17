package com.example.demorestapispringsecurity.mapper;

public interface MapperGeneric<E, T, V> {
    //E: entity
    //T: Request
    //V: Response
    E mapperRequestToEntity(T t);
    V mapperEntityToResponse(E e);
}

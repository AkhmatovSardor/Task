package com.company.Task.dto;


public interface MyCrud <K,V>{
    Response<V> create(V value);
    Response<V> get(K key);
    Response<V> update(V value,K key);
    Response<V> delete(K key);
}
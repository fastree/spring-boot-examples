package com.fastree.springboot.redis.service;


import com.fastree.springboot.redis.entity.CityEntity;

public interface CityService {
    CityEntity insert(CityEntity cityEntity);
    CityEntity update(CityEntity cityEntity);
    void deleteById(Long cityId);
    CityEntity findById(Long cityId);
}

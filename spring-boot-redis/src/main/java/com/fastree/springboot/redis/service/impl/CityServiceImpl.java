package com.fastree.springboot.redis.service.impl;

import com.fastree.springboot.redis.dao.CityRepository;
import com.fastree.springboot.redis.entity.CityEntity;
import com.fastree.springboot.redis.service.CityService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @CachePut(cacheNames = {"cache1"}, key = "#result.id")
    @Transactional
    @Override
    public CityEntity insert(CityEntity cityEntity) {
        return cityRepository.save(cityEntity);
    }

    @CachePut(cacheNames = {"cache1"}, key = "#result.id")
    @Transactional
    @Override
    public CityEntity update(CityEntity cityEntity) {
        return cityRepository.save(cityEntity);
    }

    @CacheEvict(cacheNames = {"cache1"}, key = "#cityId")
    @Transactional
    @Override
    public void deleteById(Long cityId) {
        cityRepository.deleteById(cityId);
    }

    @Cacheable(cacheNames = {"cache1"}, key = "#cityId")
    @Transactional
    @Override
    public CityEntity findById(Long cityId) {
        return cityRepository.findById(cityId).get();
    }

}

package com.fastree.springboot.redis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class CityEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String state;
}

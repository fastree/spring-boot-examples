package com.fastree.springboot.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("user")
public class UserEntity {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private AddrEntity addr = new AddrEntity(2001l, "Shanghai", "RenRen");
    private LocalDateTime time;
    @TimeToLive
    private Long expire = -1l;

    class AddrEntity {
        private Long id;
        private String city;
        private String street;

        public AddrEntity(Long id, String city, String street) {
            this.id = id;
            this.city = city;
            this.street = street;
        }
    }
}



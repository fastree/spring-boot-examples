package com.fastree.springboot.thymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

//@SpringBootTest
public class ThymeleafApplicationTest {


    @Test
    public void test() {
        System.out.println(LocalDateTime.of(2021, 1, 12, 8, 43).plusHours(7).plusMinutes(70));
    }
}

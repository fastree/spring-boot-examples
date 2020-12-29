package com.fastree.springboot.flowable;

import org.flowable.engine.RepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

//@SpringBootTest
public class FlowableApplicationTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void test() {
        System.out.println(repositoryService);
    }

    @Test
    public void test02() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 12, 28, 11, 05);
        System.out.println(dateTime.plusHours(4).plusMinutes(40));
    }
}

package com.example.menuselection.Entity;

import com.example.menuselection.repository.MyMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @Autowired
    private MyMenuRepository repository;
    @org.junit.jupiter.api.Test
    public void test(){
    }
}

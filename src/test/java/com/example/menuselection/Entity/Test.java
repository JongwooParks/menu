package com.example.menuselection.Entity;

import com.example.menuselection.domain.MenuDTO;
import com.example.menuselection.repository.MyMenuRepository;
import com.example.menuselection.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @Autowired
    private MyMenuRepository repository;
    @Autowired
    private MenuService service;
    @org.junit.jupiter.api.Test
    public void test(){
        System.out.println("==========================");
        System.out.println(service.validateMenu(new MenuDTO()));
        System.out.println("==========================");

    }

    @org.junit.jupiter.api.Test
    public void findByNameTest(){
        String name1 = "맥도날드";
        String name2 = "맥도";
        MenuDTO menuDTO1 = new MenuDTO();
        MenuDTO menuDTO2= new MenuDTO();
        menuDTO1.setMenuName(name1);
        menuDTO2.setMenuName(name2);
        System.out.println("==========================");
        System.out.println(service.validateMenu(menuDTO1));
        System.out.println("==========================");
        System.out.println("1111111111111111111");
        System.out.println(service.validateMenu(menuDTO2));
        System.out.println("1111111111111111111");
    }

    @org.junit.jupiter.api.Test
    public void test3(){
        System.out.println("11111111111111111111111111111111");
        System.out.println(repository.findAllByOrderBySelectDateDesc());
        System.out.println("11111111111111111111111111111111");
    }
}

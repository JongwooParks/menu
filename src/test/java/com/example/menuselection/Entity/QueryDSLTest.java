package com.example.menuselection.Entity;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;
import com.example.menuselection.domain.QMenu;
import com.example.menuselection.service.MenuService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.example.menuselection.domain.QMenu.*;

@SpringBootTest
public class QueryDSLTest {
    @Autowired
    private JPAQueryFactory factory;
    @Autowired
    private MenuService service;

    @Test
    public void test(){
        System.out.println(factory.selectFrom(menu) .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .fetchFirst());
    }

    @Test
    public void test2() {
        BooleanBuilder builder = new BooleanBuilder();
        List<MenuDTO> list = new ArrayList<>();
        MenuDTO dto1 = new MenuDTO();
        dto1.setMenuId(Long.valueOf(2));
        MenuDTO dto2 = new MenuDTO();
        dto2.setMenuId(Long.valueOf(1));
        list.add(dto1);
        list.add(dto2);

       if (list.size() == 1) {
            builder.and(menu.menuId.ne(list.get(0).getMenuId()));
        }
        if (list.size() == 2) {
            System.out.println(list);
            builder.and(menu.menuId.ne(list.get(0).getMenuId())).and(menu.menuId.ne(list.get(1).getMenuId()));
        }

        System.out.println(factory.selectFrom(menu).where(builder).fetch());

    }

    @Test
    public void test3(){
        /*findAllByOrderByMenuSelectDescSelectDateDescRegDtDesc*/
        factory.selectFrom(menu).orderBy(menu.menuSelect.desc(),menu.selectDate.desc(),menu.regDt.desc()).fetch().forEach(System.out::println);

    }

    @Test
    public void test4(){
        System.out.println(service.choice());
    }


}

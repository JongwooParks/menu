package com.example.menuselection.repository;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.menuselection.domain.QMenu.menu;

@Repository
@RequiredArgsConstructor
public class MyMenuCustomRepositoryImpl implements MyMenuCustomRepository {
    private final JPAQueryFactory factory;

    @Override
    public Menu choice(List<MenuDTO> list) {
        BooleanBuilder builder = new BooleanBuilder();

        if (list.size() == 1) {
            builder.and(menu.menuId.ne(list.get(0).getMenuId()));
        }
        if (list.size() == 2) {
            builder.and(menu.menuId.ne(list.get(0).getMenuId())).and(menu.menuId.ne(list.get(1).getMenuId()));
        }
        Menu menuEntity = factory.selectFrom(menu).where(builder)
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .fetchFirst();

        return menuEntity;
    }

    @Override
    public List<Menu> selectLastSelect() {
        List<Menu> list = factory.selectFrom(menu).where(menu.selectDate.isNotNull())
                .orderBy(menu.selectDate.desc()).limit(2).fetch();
        return list;
    }

    @Override
    public List<Menu> selectMenuSelectY() {
        List<Menu> list = factory.selectFrom(menu).where(menu.menuSelect.eq("Y")).fetch();
        return list;
    }


}

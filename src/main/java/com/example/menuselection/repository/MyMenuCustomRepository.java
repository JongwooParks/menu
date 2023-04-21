package com.example.menuselection.repository;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;

import java.util.List;

public interface MyMenuCustomRepository {

    public Menu choice(List<MenuDTO> list);

    public List<Menu> selectLastSelect();

    public List<Menu> selectMenuSelectY();

}

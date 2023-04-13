package com.example.menuselection.service;

import com.example.menuselection.domain.MenuDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    public List<MenuDTO> selectAll(MenuDTO dto);

    public int insertMenu(MenuDTO dto);

    public int validateMenu(MenuDTO dto);
}

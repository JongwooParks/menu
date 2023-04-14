package com.example.menuselection.service;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;
import com.example.menuselection.repository.MyMenuRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final MyMenuRepository repository;
    public static final int ERROR_CODE = 0;
    public static final int SUCCESS_CODE = 1;
    public static final int VALIDATE = 2;
    public static final int NOT_VALIDATE = 3;

    @Override
    public List<MenuDTO> selectAll(MenuDTO dto) {
        return null;
    }

    @Override
    public int registerMenu(MenuDTO dto) {
        return 0;
    }

    @Override
    public int validateMenu(MenuDTO dto) {
        String name = dto.getMenuName().trim();
        Optional<Menu> optional = repository.findMenuByMenuName(name);
        if(optional.isPresent()){
            return VALIDATE;
        }
        return NOT_VALIDATE;
    }
}

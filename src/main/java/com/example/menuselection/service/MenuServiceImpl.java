package com.example.menuselection.service;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;
import com.example.menuselection.repository.MyMenuRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final MyMenuRepository repository;
    public static final int ERROR_CODE = 0;
    public static final int SUCCESS_CODE = 1;
    public static final int VALIDATE = 2;
    public static final int NOT_VALIDATE = 3;

    @Override
    public List<MenuDTO> selectAll() {
        List<MenuDTO> list =  repository.findAllByOrderByMenuSelectDescSelectDateDescRegDtDesc().stream().map(Menu::toDTO).collect(Collectors.toList());
        List<MenuDTO> notF = list.stream().filter(i->!i.getMenuSelect().equals("F")).collect(Collectors.toList());
        if(notF.size()>=2){
            list.get(0).setExceptSelect("마지막 선택");
            list.get(1).setExceptSelect("2번째 전 선택");
        }else if(notF.size()==1){
            list.get(0).setExceptSelect("마지막 선택");
        }
        return list;
    }

    @Override
    public MenuDTO registerMenu(MenuDTO dto) {
        Menu entity = dto.toEntity();
       Menu menu = repository.save(entity);
        return menu.toDTO();
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

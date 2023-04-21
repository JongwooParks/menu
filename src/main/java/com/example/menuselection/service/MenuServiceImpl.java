package com.example.menuselection.service;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;
import com.example.menuselection.repository.MyMenuCustomRepository;
import com.example.menuselection.repository.MyMenuRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final MyMenuRepository repository;
    private final MyMenuCustomRepository customRepository;
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
        dto.setRegDt(LocalDateTime.now());
        dto.setMenuSelect("F");
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

    @Override
    public int deleteMenu(Long menuId) {
        repository.deleteById(menuId);
        return 0;
    }

    @Override
    @Transactional
    public MenuDTO choice() {
        List<MenuDTO> list = customRepository.selectLastSelect().stream().map(Menu::toDTO).collect(Collectors.toList());
        MenuDTO dto = customRepository.choice(list).toDTO();
        Optional<Menu> optionalMenu = repository.findById(dto.getMenuId());
        if(optionalMenu.isEmpty()){
            return null;
        }

        customRepository.selectMenuSelectY().forEach(i->i.setMenuSelect("N"));

        Menu menu = optionalMenu.get();
        menu.setMenuSelect("Y");
        menu.setSelectDate(LocalDateTime.now());

        return dto;
    }




}

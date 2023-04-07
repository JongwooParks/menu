package com.example.menuselection.controller;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;
import com.example.menuselection.repository.MyMenuRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MyMenuRepository repository;
    @GetMapping("/test")
    public String test(){

        return "test";
    }

    @GetMapping("/main")
    public String select(MenuDTO dto, Model model){

        List<MenuDTO> menuList = repository.findAll(Sort.by(Sort.Direction.ASC,"menuId")).stream().map(Menu::toDTO).collect(Collectors.toList());
        System.out.println("=================================");
        System.out.println(menuList);
        System.out.println("=================================");
        model.addAttribute("list", menuList);
        return "select";
    }

}

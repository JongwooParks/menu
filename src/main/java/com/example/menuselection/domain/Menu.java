package com.example.menuselection.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="our_menu")
@Getter @Setter @ToString
@NoArgsConstructor
public class Menu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_id")
    private Long menuId;
    @Column(name="menu_name")
    private String menuName;
    @Column(name="select_date")
    private LocalDate selectDate;
    @Column(name="menu_select")
    private String menuSelect;
    @Column(name="menu_category")
    private String menuCategory;

    public MenuDTO toDTO(){
        MenuDTO dto = new MenuDTO();
        dto.setMenuId(this.menuId);
        dto.setMenuName(this.menuName);
        dto.setMenuSelect(this.menuSelect);
        dto.setSelectDate(this.selectDate);
        dto.setMenuCategory(this.menuCategory);
        return dto;
    }

    @Builder
    public Menu(String menuName, LocalDate selectDate, String menuSelect, String menuCategory) {
        this.menuName = menuName;
        this.selectDate=selectDate;
        this.menuSelect = menuSelect;
        this.menuCategory = menuCategory;
    }
}

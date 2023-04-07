package com.example.menuselection.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="our_menu")
@Data
public class Menu {
    @Id @GeneratedValue()
    @Column(name="menu_id")
    private Long menuId;
    @Column(name="menu_name")
    private String menuName;
    @Column(name="chkYn")
    private String chkYn;
    @Column(name="menu_select")
    private String menuSelect;
    @Column(name="menu_category")
    private String menuCategory;

    public MenuDTO toDTO(){
        MenuDTO dto = new MenuDTO();
        dto.setMenuId(this.menuId);
        dto.setMenuName(this.menuName);
        dto.setMenuSelect(this.menuSelect);
        dto.setChkYn(this.chkYn);
        dto.setMenuCategory(this.menuCategory);
        return dto;
    }

}

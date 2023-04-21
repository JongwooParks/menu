package com.example.menuselection.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
public class MenuDTO {
    private Long menuId;
    private String menuName;
    private LocalDateTime selectDate;
    private String menuSelect;
    private String menuCategory;

    private LocalDateTime regDt;

    private String exceptSelect;


    public Menu toEntity(){
        Menu menu = Menu.builder()
                .menuCategory(this.menuCategory)
                .menuName(this.menuName)
                .selectDate(this.selectDate)
                .menuSelect(this.menuSelect)
                .regDt(this.regDt)
                .build();
        return menu;
    }
}

package com.example.menuselection.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
public class MenuDTO {
    private Long menuId;
    private String menuName;
    private String chkYn;
    private String menuSelect;
    private String menuCategory;
}

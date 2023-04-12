package com.example.menuselection.controller;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuRestController {


    @PutMapping("/validate")
    public ResponseEntity<?> validateMenu(MenuDTO data, BindingResult result){
        if(result.hasErrors()){
           return checkError(result);
        }
        return new ResponseEntity<MenuDTO>(data,HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> insert(MenuDTO data, BindingResult result){
        if(result.hasErrors()){
            return checkError(result);
        }
        return new ResponseEntity<MenuDTO>(data,HttpStatus.OK);
    }

    public ResponseEntity<?> checkError(BindingResult result){
        Map<String, String> errorMap = new HashMap<>();
        for(FieldError error : result.getFieldErrors()){
            errorMap.put(error.getField(),error.getDefaultMessage());
        }
        return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
    }


}

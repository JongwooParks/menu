package com.example.menuselection.controller;

import com.example.menuselection.domain.Menu;
import com.example.menuselection.domain.MenuDTO;
import com.example.menuselection.service.MenuService;
import com.example.menuselection.service.MenuServiceImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MenuRestController {
    private final MenuService service;

    @PutMapping("/validate")
    public ResponseEntity<?> validateMenu(MenuDTO data, BindingResult result){

        if(result.hasErrors()){
           return checkError(result);
        }
        try{
            int code = service.validateMenu(data);
            return new ResponseEntity<Integer>(code,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> insert(MenuDTO data, BindingResult result){
        if(result.hasErrors()){
            return checkError(result);
        }
        if(data == null){
            return new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
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

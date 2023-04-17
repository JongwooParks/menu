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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuRestController {
    private final MenuService service;

    @GetMapping("/show")
    public ResponseEntity<?> showAllMenu(){
        List<MenuDTO> list = service.selectAll();
        try {
            list = service.selectAll();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
        return new ResponseEntity<List<MenuDTO>>(list,HttpStatus.OK);
    }

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
        int code = 0;
        if(result.hasErrors()){
            return checkError(result);
        }
        if(data == null){
            return new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
        }
        if(service.registerMenu(data) != null){
            code = 1;
        }
        return new ResponseEntity<Integer>(code,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(String menuId, BindingResult result){
        int code = 0;
        if(result.hasErrors()){
            return checkError(result);
        }
        if(menuId == null || menuId.equals("")){
            return new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Integer>(code,HttpStatus.OK);
    }

    public ResponseEntity<?> checkError(BindingResult result){
        Map<String, String> errorMap = new HashMap<>();
        for(FieldError error : result.getFieldErrors()){
            errorMap.put(error.getField(),error.getDefaultMessage());
        }
        return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
    }


}

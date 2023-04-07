package com.example.menuselection.repository;

import com.example.menuselection.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyMenuRepository extends JpaRepository<Menu,Long> {

}

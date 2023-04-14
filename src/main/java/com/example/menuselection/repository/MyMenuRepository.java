package com.example.menuselection.repository;

import com.example.menuselection.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyMenuRepository extends JpaRepository<Menu,Long> {
    public Optional<Menu> findMenuByMenuName(String name);

    public List<Menu> findAllByOrderBySelectDateDesc();

}

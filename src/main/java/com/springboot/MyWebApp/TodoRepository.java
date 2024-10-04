package com.springboot.MyWebApp;

import com.springboot.MyWebApp.todo.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<ToDo, Integer> {
    public List<ToDo> findByUsername(String username);
}

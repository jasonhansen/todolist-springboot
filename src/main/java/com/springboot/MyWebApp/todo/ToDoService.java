package com.springboot.MyWebApp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ToDoService {
    private static List<ToDo> todos = new ArrayList<>();

    private static int todosCount = 0;

    static {
        todos.add(new ToDo(++todosCount,"hansen","Learn AWS", LocalDate.now().plusYears(1),false));
        todos.add(new ToDo(++todosCount,"hansen","Learn Java Development", LocalDate.now().plusYears(2),false));
        todos.add(new ToDo(++todosCount,"hansen","Learn Microservices", LocalDate.now().plusYears(3),false));
    }

    public List<ToDo> findByUsername(String username) {

        Predicate<? super ToDo> predicate = toDo -> toDo.getUsername().equalsIgnoreCase(username);

        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        ToDo todo = new ToDo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
        // lambda function
        Predicate<? super ToDo> predicate = toDo -> toDo.getId() == id;
        todos.removeIf(predicate);
    }

    public ToDo findById(int id) {
        Predicate<? super ToDo> predicate = toDo -> toDo.getId() == id;
        ToDo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid ToDo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}

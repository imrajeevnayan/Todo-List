package com.example.todo.controller;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestParam String title) {
        return ResponseEntity.status(201).body(todoService.createTodo(title));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateTodo(@PathVariable Long id, @RequestParam String title) {
        return todoService.updateTodo(id, title);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Object> updateTodoStatus(@PathVariable Long id) {
        return todoService.updateTodoStatus(id);
    }
}
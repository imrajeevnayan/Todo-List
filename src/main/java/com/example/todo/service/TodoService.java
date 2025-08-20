package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    public ResponseEntity<Object> updateTodo(Long id, String title) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isEmpty()) {
            return new ResponseEntity<>("Todo not found", HttpStatus.NOT_FOUND);
        }
        Todo todo = existingTodo.get();
        todo.setTitle(title);
        todoRepository.save(todo);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteTodo(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isEmpty()) {
            return new ResponseEntity<>("Todo not found", HttpStatus.NOT_FOUND);
        }
        todoRepository.deleteById(id);
        return new ResponseEntity<>("Todo deleted", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateTodoStatus(Long id) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isEmpty()) {
            return new ResponseEntity<>("Todo not found", HttpStatus.NOT_FOUND);
        }
        Todo todo = existingTodo.get();
        todo.setCompleted(true);
        todoRepository.save(todo);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
}
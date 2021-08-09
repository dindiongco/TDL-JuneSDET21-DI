package com.qa.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.models.ToDo;
import com.qa.services.ToDoService;

@RestController
@RequestMapping("/todo")
public class ToDoController {
	
	private ToDoService toDoService;

	@Autowired
	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<ToDo>> getAllToDos() {
		return new ResponseEntity<List<ToDo>>(this.toDoService.getAllToDos(), HttpStatus.OK);
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable("id") Long id) {
		return new ResponseEntity<ToDo>(this.toDoService.getToDoById(id), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ToDo> createTask(@Valid @RequestBody ToDo task) {
		ToDo data = this.toDoService.createToDo(task);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("localhost:8080/" + data.getTask()));
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ToDo>(data, headers, HttpStatus.CREATED);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ToDo> updateTaskById(@PathVariable("id") Long id, @Valid @RequestBody ToDo task) {
		return new ResponseEntity<ToDo>(this.toDoService.updateToDoById(id, task), HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTaskById(@PathVariable("id") Long id) {		
		return new ResponseEntity<>(this.toDoService.deleteToDoById(id), HttpStatus.OK);
	}

}

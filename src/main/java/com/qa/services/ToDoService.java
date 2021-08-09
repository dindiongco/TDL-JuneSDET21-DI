package com.qa.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.models.ToDo;
import com.qa.repositories.ToDoRepository;

@Service
public class ToDoService {
	
	private ToDoRepository toDoRepository;

	@Autowired
	public ToDoService(ToDoRepository toDoRepository) {
		this.toDoRepository = toDoRepository;
	}

	public List<ToDo> getAllToDos() {
		List<ToDo> toDosInDb = toDoRepository.findAll();

		return toDosInDb;
	}

	public ToDo getToDoById(Long id) {
		return this.toDoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public ToDo createToDo(ToDo task) {
		ToDo savedToDo = this.toDoRepository.saveAndFlush(task);

		return savedToDo;
	}

	public ToDo updateToDoById(Long id, ToDo task) {
//		if (!taskRepository.existsById(taskId))
//			throw new EntityNotFoundException();

		ToDo toDoInDb = toDoRepository.findById(id).get();

		toDoInDb.setTask(task.getTask());

		ToDo updatedToDo = this.toDoRepository.saveAndFlush(toDoInDb);
		return updatedToDo;
	}

	public String deleteToDoById(Long id) {
//		if (!taskRepository.existsById(taskId))
//			throw new EntityNotFoundException();

		toDoRepository.deleteById(id);
		if (toDoRepository.existsById(id)) {
			return "Not deleted" + id;
		}
		else {
			return id + " has been deleted.";
		}
	}

}

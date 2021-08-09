package com.qa.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String task;
	
	public ToDo() {
	}
	
	public ToDo(String task) {
		this.task = task;
	}
	
	public ToDo(Long id, String task) {
		this.id = id;
		this.task = task;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, task);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDo other = (ToDo) obj;
//		return Objects.equals(id, other.id) && Objects.equals(task, other.task);
		return true;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", task=" + task + "]";
	}

}

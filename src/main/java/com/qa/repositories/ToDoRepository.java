package com.qa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.models.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{

}

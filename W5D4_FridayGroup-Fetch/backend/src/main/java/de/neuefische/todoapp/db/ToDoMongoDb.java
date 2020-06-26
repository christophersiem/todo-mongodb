package de.neuefische.todoapp.db;

import de.neuefische.todoapp.model.ToDo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ToDoMongoDb extends PagingAndSortingRepository<ToDo,String> {
}

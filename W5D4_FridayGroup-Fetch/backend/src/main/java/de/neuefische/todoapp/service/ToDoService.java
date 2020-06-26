package de.neuefische.todoapp.service;


import de.neuefische.todoapp.db.ToDoMongoDb;
import de.neuefische.todoapp.model.ToDo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class ToDoService {

  public final ToDoMongoDb toDoDB;

  @Autowired
  public ToDoService(ToDoMongoDb toDoDB) {
    this.toDoDB = toDoDB;
  }

    public Iterable<ToDo> getToDo() {
        return toDoDB.findAll();
    }

  public ToDo addNewToDo(ToDo toDo) {
    return toDoDB.save(toDo);
  }

    public ToDo changeToDoStatus(String id, ToDo updateToDo) {
      Optional<ToDo> result = toDoDB.findById(id);
      if (result.isEmpty()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      ToDo toDo = result.get();
      toDo.setStatus(updateToDo.getStatus());
      toDoDB.save(toDo);
      return toDo;

    }

    public void deleteToDo(String id) {
      Optional<ToDo> result = toDoDB.findById(id);
      if (result.isEmpty()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      ToDo toDo = result.get();
      toDoDB.delete(toDo);
    }

}

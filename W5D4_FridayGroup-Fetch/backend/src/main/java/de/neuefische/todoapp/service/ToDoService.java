package de.neuefische.todoapp.service;


import de.neuefische.todoapp.db.ToDoMongoDb;
import de.neuefische.todoapp.model.ToDo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    public ToDo changeToDoStatus(String id, ToDo updateToDo) {
//            return toDoDB.
//    }
//
//    public List<ToDo> deleteToDo(String id) {
//       return toDoDB.deleteOne();
//    }

}

package de.neuefische.todoapp.service;


import de.neuefische.todoapp.db.ToDoDB;
import de.neuefische.todoapp.model.ToDo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Data
public class ToDoService {

    public final ToDoDB toDoDB;

   @Autowired
    public ToDoService(ToDoDB toDoDB) {
        this.toDoDB = toDoDB;
    }

    public List<ToDo> getToDo() {
        return toDoDB.getToDo();
    }

    public ToDo addNewToDo(ToDo toDo) {
        return toDoDB.addNewToDo(toDo);
    }

    public ToDo changeToDoStatus(String id, ToDo updateToDo) {
            return toDoDB.changeToDoStatus(id, updateToDo);
    }

    public List<ToDo> deleteToDo(String id) {
       return toDoDB.deleteToDo(id);
    }

}

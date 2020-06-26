package de.neuefische.todoapp.db;

import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.model.ToDoStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ToDoDB {

    private List<ToDo> toDoList = new ArrayList<>(List.of(

    ));

    public List<ToDo> getToDo() {
        return toDoList;
    }

    public ToDo addNewToDo(ToDo toDo) {
        toDo.setId(UUID.randomUUID().toString());
        toDo.setStatus(ToDoStatus.OPEN);
        toDoList.add(toDo);
        return toDo;
    }

    public ToDo changeToDoStatus(String id, ToDo updateToDo) {
        for (ToDo toDo : toDoList) {
            if (toDo.getId().equals(id)) {
                toDo.setStatus(updateToDo.getStatus());
                return toDo;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message ID not found!");
    }

    public List<ToDo> deleteToDo(String id) {
        for (ToDo toDo : toDoList) {
            if (toDo.getId().equals(id)) {
                toDoList.remove(toDo);
                return toDoList;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Message to Delete!");
    }

}

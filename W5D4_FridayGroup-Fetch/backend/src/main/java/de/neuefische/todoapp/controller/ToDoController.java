package de.neuefische.todoapp.controller;


import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.model.ToDoStatus;
import de.neuefische.todoapp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/todo")
public class ToDoController {

    public final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @GetMapping
    public Iterable<ToDo> getToDo() {
        return toDoService.getToDo();
    }

    @PutMapping
    public ToDo addNewToDo(@RequestBody ToDo toDo) {
        return toDoService.addNewToDo(toDo);
    }

   @PutMapping("{id}/status")
   public ToDo changeToDoStatus(@PathVariable String id, @RequestBody ToDo updateToDo) {
        return toDoService.changeToDoStatus(id, updateToDo);
   }

    @DeleteMapping("{id}")
    public void deleteToDo(@PathVariable String id) {
        toDoService.deleteToDo(id);
    }

}

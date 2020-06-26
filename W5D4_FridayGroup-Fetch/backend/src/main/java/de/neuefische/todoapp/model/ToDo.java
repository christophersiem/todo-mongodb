package de.neuefische.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo{

    private String id;
    private String description;
    private ToDoStatus status;

}

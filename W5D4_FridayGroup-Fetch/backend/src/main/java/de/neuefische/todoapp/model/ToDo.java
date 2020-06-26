package de.neuefische.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

  @Id
  private String id;
  private String description;
  private ToDoStatus status;

}

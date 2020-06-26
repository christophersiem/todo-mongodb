package de.neuefische.todoapp.model;

import com.mongodb.client.model.Collation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "todo")
public class ToDo {

  @Id
  private String id;
  private String description;
  private ToDoStatus status;

}

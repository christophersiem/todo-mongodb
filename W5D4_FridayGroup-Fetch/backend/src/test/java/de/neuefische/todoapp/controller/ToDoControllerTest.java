package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.model.ToDoStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplateExtensionsKt;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getToDoShouldReturnEmptyToDoArray() {
        //Given

        //When
        ResponseEntity<ToDo[]> response = testRestTemplate.getForEntity("http://localhost:" + port + "/api/todo", ToDo[].class);
        HttpStatus statusCode = response.getStatusCode();
        ToDo[] toDo = response.getBody();

        //Then
        assertEquals( HttpStatus.OK, statusCode);
        assertEquals( 0, toDo.length);

    }

    @Test
    public void getToDoShouldReturnNewlyAddedToDo() {
        //POST
        ResponseEntity<ToDo> postResponse = testRestTemplate.postForEntity("http://localhost:" + port + "/api/todo",new ToDo("", "Buy Milk", ToDoStatus.OPEN),ToDo.class);
        assertEquals( HttpStatus.OK, postResponse.getStatusCode());

        //When
        ResponseEntity<ToDo[]> response = testRestTemplate.getForEntity("http://localhost:" + port + "/api/todo", ToDo[].class);
        HttpStatus statusCode = response.getStatusCode();
        ToDo[] toDo = response.getBody();
        ToDo newToDo = toDo[toDo.length-1];
        String id = newToDo.getId();

        //Then
        assertEquals( HttpStatus.OK, statusCode);
        assertEquals( 1, toDo.length);
        assertEquals( new ToDo(id, "Buy Milk", ToDoStatus.OPEN), toDo[0]);

    }

    @Test
    public void updateToDoStatusFromOpenToInProgress() {
        //PUT
        ToDo updateToDo = new ToDo("1", "Clean the Car", ToDoStatus.OPEN);
        HttpEntity<ToDo> requestEntity = new HttpEntity<>(updateToDo);
        ResponseEntity<ToDo> putResponse = testRestTemplate.exchange("http://localhost:" + port + "/api/todo/{id}/status", HttpMethod.PUT, requestEntity, ToDo.class);
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());

        //When
        ResponseEntity<ToDo[]> response = testRestTemplate.getForEntity("http://localhost:" + port + "/api/todo/{id}/status", ToDo[].class);
        HttpStatus statusCode = response.getStatusCode();
        ToDo[] toDo = response.getBody();
        ToDo newToDo = toDo[0];
        String status = newToDo.getStatus().toString();

        //Then
        assertEquals( HttpStatus.OK, statusCode);
        assertEquals( 1, toDo.length);
        assertEquals( new ToDo("1", "Buy Milk", ToDoStatus.IN_PROGRESS), toDo[0]);

    }



}
package com.Tasks;

import com.Tasks.ToDo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {
    private List<ToDo> toDoList = new ArrayList<>();

    @PostMapping("/createTask")
    public ResponseEntity<String> createTasks (@RequestBody ToDo request){

        String title = request.getTitle();
        String description = request.getDescription();

        ToDo toDo = new ToDo(title, description);

        toDo.setTitle(title);
        toDo.setDescription(description);
        toDoList.add(toDo);

        return ResponseEntity.ok("Task created");
    }

    @DeleteMapping("deleteTask/{title}")
    public ResponseEntity<String> deleteTasks (@PathVariable("title") String title) {
        boolean removed = toDoList.removeIf(toDo -> toDo.getTitle().equalsIgnoreCase(title));

        if (removed) {
            return ResponseEntity.ok("Uppgiften har tagits bort");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "getTasks")
    public ResponseEntity<List> getTasks (){
        if (toDoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(toDoList);
    }





}


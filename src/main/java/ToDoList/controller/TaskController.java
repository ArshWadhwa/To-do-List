package ToDoList.controller;

import ToDoList.entity.TaskEntity;
import ToDoList.taskData.*;
import ToDoList.taskData.db.TaskDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
@RequestMapping("/task")
public class TaskController {

    private TaskDbRepository taskDbRepository;

    public TaskController(TaskDbRepository taskDbRepository) {
        this.taskDbRepository = taskDbRepository;
    }
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public void AddTaskResponse (@RequestBody AddTaskRequest addTaskRequest) {
        System.out.println("Add task Requested for id: " + addTaskRequest.getTask());
        TaskEntity task = new TaskEntity();
        task.setTask(addTaskRequest.getTask().getTask());
        task.setIsCompleted(addTaskRequest.getTask().getIsCompleted());
        task.setUpdatedAt(Instant.now());
        task.setCreatedAt(Instant.now());
        taskDbRepository.save(task);


    }
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public GetTaskResponse getTaskResponse(){
        List<TaskEntity> taskEntityList = taskDbRepository.findAll();

        GetTaskResponse response = new GetTaskResponse();
        List<GetTask> taskList = new ArrayList<>();

        taskEntityList.forEach(taskEntity ->{

            GetTask task = new GetTask();
            task.setId(taskEntity.getId());
            task.setTask(taskEntity.getTask());
            task.setIsCompleted(taskEntity.getIsCompleted());
            task.setIsCreatedAt(taskEntity.getCreatedAt());
            task.setIsUpdatedAt(taskEntity.getUpdatedAt());
            taskList.add(task);

        });
        response.setGetTask(taskList);
        return response;
    }

    @PutMapping(
            path = "{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> editTask(@PathVariable("id") Long id, @RequestBody UpdateTask updateTask) {
        Optional<TaskEntity> taskEntityOptional = taskDbRepository.findById(id);
        if (taskEntityOptional.isPresent()) {
            taskDbRepository.updateTask(updateTask.getNewTask(),updateTask.getUpdatedIsCompleted(),id); // Save the updated task
            return ResponseEntity.ok("Task updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }


    //Delete mapping
    @DeleteMapping(
            path="{id}"
    )
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id){
        Optional<TaskEntity> taskEntityOptional = taskDbRepository.findById(id);

        if(taskEntityOptional.isPresent()){
            TaskEntity taskEntity= taskEntityOptional.get();
            taskDbRepository.delete(taskEntityOptional.get());
            return ResponseEntity.ok("task deleted with ID: " +taskEntity.getId()+" successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with Id");
        }
    }

}




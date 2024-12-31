package ToDoList.controller;

import ToDoList.entity.TaskEntity;
import ToDoList.taskData.*;
import ToDoList.taskData.db.TaskDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public GetTaskResponse getTaskResponse(){
        List<TaskEntity> taskEntityList = taskDbRepository.findAll();

        GetTaskResponse response = new GetTaskResponse();
        List<GetTask> taskList = new ArrayList<>();

        taskEntityList.forEach(taskEntity ->{
            GetTask task = new GetTask();
            task.setTask(taskEntity.getTask());
            task.setTask(String.valueOf(taskEntity.getIsCompleted()));
            task.setTask(String.valueOf(taskEntity.getCreatedAt()));
            task.setTask(String.valueOf(taskEntity.getUpdatedAt()));
            taskList.add(task);

        });
        response.setGetTask(taskList);
        return response;
    }

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public void editTask(@PathVariable("id") Long id,@RequestBody UpdateTask updateTask){
        TaskEntity existingTask = taskDbRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        existingTask.setTask(updateTask.getTask());
        existingTask.setIsCompleted(updateTask.getIsCompleted());
        existingTask.setUpdatedAt(Instant.now());

        taskDbRepository.save(existingTask);
    }


    //Delete mapping
    @DeleteMapping(
            path="/delete"
    )
    public void deleteTask(@PathVariable("id") Long id,@RequestBody DeleteTask deleteTask){
        TaskEntity existingTask = taskDbRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with this id"));
        taskDbRepository.delete(existingTask);
    }

}




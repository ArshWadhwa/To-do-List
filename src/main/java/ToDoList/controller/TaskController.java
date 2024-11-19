package ToDoList.controller;

import ToDoList.entity.TaskEntity;
import ToDoList.taskData.AddTaskRequest;
import ToDoList.taskData.GetTask;
import ToDoList.taskData.GetTaskResponse;
import ToDoList.taskData.Task;
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
}
//
//    @GetMapping(
//            produces = {MediaType.APPLICATION_JSON_VALUE},
//            consumes = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    public GetTaskResponse getTaskResponse() {
//        List<TaskEntity> coffeeEntityList = TaskDbRepository.findAll();
//
//        GetTaskResponse response = new GetTaskResponse();
//        List<GetTask> getTaskList = new ArrayList<>();
//        getTaskList.forEach(taskEntity -> {
//            Task task = new Task();
//
//            task.setName(coffeeEntity.getName());
//
//        });
//        response.setCoffeeList(coffeeList);
//        return response;
//
//}

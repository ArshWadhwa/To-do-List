package ToDoList.taskData;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    public String task;
    public Boolean isCompleted;
    public LocalDateTime isCreatedAt;
    public LocalDateTime isUpdatedAt;

//    public String getTask(){
//        return task;
//    }
//    public String setTask(){
//        this.task=task;
//    }
}

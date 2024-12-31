package ToDoList.taskData;

import lombok.Data;

@Data
public class UpdateTask {
    public String newTask;
    public  Boolean updatedIsCompleted;

}

package ToDoList.taskData;

import lombok.Data;

import java.util.List;
@Data

public class GetTaskResponse {
    List<GetTask> getTask;
}

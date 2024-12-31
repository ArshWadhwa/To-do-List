package ToDoList.taskData;


import jakarta.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class GetTask {
    public String task;
    public Boolean isCompleted;
    public LocalDateTime isCreatedAt;
    public LocalDateTime isUpdatedAt;




}

package ToDoList.taskData;


import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class GetTask {
    public Long id;
    public String task;
    public Boolean isCompleted;
    public Instant isCreatedAt;
    public Instant isUpdatedAt;




}

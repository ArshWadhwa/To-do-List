package ToDoList.taskData;


import jakarta.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class GetTask extends Task{
    private Integer id;
    private LocalDateTime isCreatedAt;
    private LocalDateTime isUpdatedAt;


}

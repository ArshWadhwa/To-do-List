package ToDoList.entity;

import ToDoList.taskData.Task;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
@Entity
@Data
@Table(name = "task")
public class TaskEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(updatable = false)
        private String task;

        @Column(name = "is_completed")
        private Boolean isCompleted;

        @Column(updatable = false)
        private Instant createdAt;

        @Column(nullable = false)
        private Instant updatedAt;


}




package ToDoList.taskData.db;

import ToDoList.entity.TaskEntity;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TaskDbRepository extends JpaRepository<TaskEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "update task set task = ?1 , is_completed = ?2 , updated_at = now() where id = ?3",nativeQuery = true)
    void updateTask(String taskName, Boolean isCompleted,Long id);
}

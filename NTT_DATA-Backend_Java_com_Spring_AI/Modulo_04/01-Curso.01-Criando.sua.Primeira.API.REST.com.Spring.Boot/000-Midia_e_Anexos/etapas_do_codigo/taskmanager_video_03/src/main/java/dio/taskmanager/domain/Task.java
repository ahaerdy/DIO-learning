package dio.taskmanager.domain;

import lombok.Getter;
import org.springframework.util.Assert;
import java.util.Optional;

@Getter
public class Task {
    private final TaskId id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description) {
        this.id = new TaskId(); // gera um novo identificador
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    public TaskId getId() {
        return id;
    }
}

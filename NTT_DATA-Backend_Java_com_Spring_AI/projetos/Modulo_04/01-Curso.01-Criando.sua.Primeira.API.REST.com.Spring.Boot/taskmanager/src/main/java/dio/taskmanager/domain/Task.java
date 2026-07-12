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

    public void update(Optional<String> title, Optional<String> description,
                       Optional<TaskStatus> status) {
        title.ifPresent(value -> this.title = value);
        description.ifPresent(value -> this.description = Optional.of(value));
        status.ifPresent(value -> this.status = value);
    }

    public TaskId getId() {
        return id;
    }
}

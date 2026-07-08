package dio.taskmanager.domain;

import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

public class Task {
    private String id;
    private String title;
    // private String description;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description, TaskStatus status) {
        Assert.notNull(title, "Title must not be null");

        // this.id = id;
        this.id = new TaskId();
        this.title = title;
        this.description = description;
        // this.status = status;
        this.status = TaskStatus.PENDING;

    }
}

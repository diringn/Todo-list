import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String title;
    private String description;
    private boolean isCompleted;
    private LocalDateTime createdAt;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;
        this.createdAt = LocalDateTime.now();
    }

    public Task(String title, String description, int id, String createdAt, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.isCompleted = isCompleted;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdAt = LocalDateTime.parse(createdAt, formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getCreatedAtString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.createdAt.format(formatter);
    }

    @Override
    public String toString() {
        String createdTime = createdAt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        return String.format(
                "[ID %d] %s\nОписание: %s\nСтатус: %s\nСоздано: %s",
                id,
                title,
                description,
                isCompleted ? "Выполнено" : "В процессе",
                createdTime
        );
    }
}

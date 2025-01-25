import java.util.List;

public class TaskManager {
    private final ITaskRepository taskRepository;

    public TaskManager(ITaskRepository repository) {
        this.taskRepository = repository;
    }

    public void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        int generatedId = taskRepository.addTask(newTask);
        if (generatedId != -1) {
            System.out.println("Задача добавлена с ID: " + generatedId);
        } else {
            System.out.println("Произошла ошибка при добавлении задачи.");
        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public boolean deleteTask(int taskId) {
        boolean result = taskRepository.deleteTask(taskId);
        if (result) {
            System.out.println("Задача с ID " + taskId + " успешно удалена.");
        } else {
            System.out.println("Задача с ID " + taskId + " не найдена или произошла ошибка.");
        }
        return result;
    }

    public boolean updateTask(int taskId, String newTitle, String newDescription) {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            System.out.println("Задача с ID " + taskId + " не найдена.");
            return false;
        }
        if (newTitle != null && !newTitle.trim().isEmpty()) {
            task.setTitle(newTitle);
        }
        if (newDescription != null && !newDescription.trim().isEmpty()) {
            task.setDescription(newDescription);
        }
        boolean updated = taskRepository.updateTask(task);
        if (updated) {
            System.out.println("Задача с ID " + taskId + " обновлена.");
        } else {
            System.out.println("Произошла ошибка при обновлении задачи.");
        }
        return updated;
    }

    public boolean completeTask(int taskId) {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            System.out.println("Задача с ID " + taskId + " не найдена.");
            return false;
        }
        task.setCompleted(true);
        boolean result = taskRepository.updateTask(task);
        if (result) {
            System.out.println("Задача с ID " + taskId + " установлена как выполненная.");
        } else {
            System.out.println("Произошла ошибка при обновлении задачи.");
        }
        return result;
    }
}

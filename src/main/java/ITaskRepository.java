import java.util.List;

public interface ITaskRepository {

    /**
     * Добавить задачу в хранилище.
     * @param task задача для добавления
     * @return ID задачи (сгенерированный базой) или -1 при ошибке
     */
    int addTask(Task task);

    /**
     * Получить список всех задач.
     * @return список задач
     */
    List<Task> getAllTasks();

    /**
     * Удалить задачу по ID.
     * @param taskId ID задачи
     * @return true, если задача удалена, иначе false
     */
    boolean deleteTask(int taskId);

    /**
     * Обновить задачу.
     * @param task задача с обновлёнными данными
     * @return true, если задача успешно обновлена, иначе false
     */
    boolean updateTask(Task task);

    /**
     * Найти задачу по ID.
     * @param taskId ID задачи
     * @return задача или null, если не найдена
     */
    Task findTaskById(int taskId);
}

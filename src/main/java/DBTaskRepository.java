import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTaskRepository implements ITaskRepository {

    private static final String DB_URL = "jdbc:h2:./tasks";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Не удалось загрузить драйвер H2: " + e.getMessage());
        }
    }

    public DBTaskRepository() {
        createTableIfNotExists();
    }

   private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS tasks ("
                + "id INTEGER AUTO_INCREMENT PRIMARY KEY, "
                + "title VARCHAR(255) NOT NULL, "
                + "description TEXT, "
                + "isCompleted BOOLEAN DEFAULT FALSE, "
                + "createdAt VARCHAR(19) NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);

        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }

    @Override
    public int addTask(Task task) {
        String insertSQL = "INSERT INTO tasks (title, description, isCompleted, createdAt) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setBoolean(3, task.isCompleted());
            pstmt.setString(4, task.getCreatedAtString());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        task.setId(generatedId);
                        return generatedId;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении задачи: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String selectSQL = "SELECT id, title, description, isCompleted, createdAt FROM tasks";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("id"),
                        rs.getString("createdAt"),
                        rs.getBoolean("isCompleted")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при чтении задач: " + e.getMessage());
        }
        return tasks;
    }

    @Override
    public boolean deleteTask(int taskId) {
        String deleteSQL = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, taskId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении задачи: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateTask(Task task) {
        String updateSQL = "UPDATE tasks SET title = ?, description = ?, isCompleted = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setBoolean(3, task.isCompleted());
            pstmt.setInt(4, task.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении задачи: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Task findTaskById(int taskId) {
        String selectSQL = "SELECT id, title, description, isCompleted, createdAt FROM tasks WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, taskId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Task(
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getInt("id"),
                            rs.getString("createdAt"),
                            rs.getBoolean("isCompleted")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске задачи: " + e.getMessage());
        }
        return null;
    }
}

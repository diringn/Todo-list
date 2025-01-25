import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ITaskRepository dbRepository = new DBTaskRepository();

        TaskManager taskManager = new TaskManager(dbRepository);

        boolean exit = false;
        System.out.println("Добро пожаловать в To-do list приложение (H2 Database + Maven)!");

        while (!exit) {
            printMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addNewTask(taskManager);
                    break;
                case "2":
                    showAllTasks(taskManager);
                    break;
                case "3":
                    updateTask(taskManager);
                    break;
                case "4":
                    completeTask(taskManager);
                    break;
                case "5":
                    deleteTask(taskManager);
                    break;
                case "0":
                    exit = true;
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неправильный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Просмотреть все задачи");
        System.out.println("3. Редактировать задачу");
        System.out.println("4. Отметить задачу выполненной");
        System.out.println("5. Удалить задачу");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void addNewTask(TaskManager taskManager) {
        System.out.print("Введите заголовок задачи: ");
        String title = scanner.nextLine();

        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();

        taskManager.addTask(title, description);
    }

    private static void showAllTasks(TaskManager taskManager) {
        List<Task> tasks = taskManager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
            return;
        }
        System.out.println("\nСписок задач:");
        for (Task task : tasks) {
            System.out.println(task);
            System.out.println("--------------------------------------------------");
        }
    }

    private static void updateTask(TaskManager taskManager) {
        System.out.print("Введите ID задачи для редактирования: ");
        int taskId = readIntOrDefault(-1);
        if (taskId == -1) {
            System.out.println("Неверный ввод. Операция прервана.");
            return;
        }

        System.out.print("Введите новый заголовок (или оставьте пустым, чтобы не менять): ");
        String newTitle = scanner.nextLine();

        System.out.print("Введите новое описание (или оставьте пустым, чтобы не менять): ");
        String newDescription = scanner.nextLine();

        taskManager.updateTask(taskId, newTitle, newDescription);
    }

    private static void completeTask(TaskManager taskManager) {
        System.out.print("Введите ID задачи, которую нужно отметить выполненной: ");
        int taskId = readIntOrDefault(-1);
        if (taskId == -1) {
            System.out.println("Неверный ввод. Операция прервана.");
            return;
        }

        taskManager.completeTask(taskId);
    }

    private static void deleteTask(TaskManager taskManager) {
        System.out.print("Введите ID задачи для удаления: ");
        int taskId = readIntOrDefault(-1);
        if (taskId == -1) {
            System.out.println("Неверный ввод. Операция прервана.");
            return;
        }

        taskManager.deleteTask(taskId);
    }

    private static int readIntOrDefault(int defaultValue) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}

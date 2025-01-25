# Todo List

**Todo List** is a console-based Java application designed to help you manage your tasks efficiently. This project demonstrates the application of **Object-Oriented Programming (OOP)** principles, integration with the **H2 Database** for data storage, and dependency management using **Maven**.

## Features

- **Add Tasks:** Create new tasks with a title and description.
- **View Tasks:** Display a list of all current tasks.
- **Edit Tasks:** Modify the title and/or description of existing tasks.
- **Mark as Completed:** Update the status of tasks to indicate completion.
- **Delete Tasks:** Remove tasks that are no longer needed.

## Technologies Used

- **Programming Language:** Java 8+
- **Database:** H2 Database (Embedded Relational Database)
- **Build Tool:** Maven
- **OOP Principles:** Abstraction, Encapsulation, Polymorphism

## Project Structure


- **`pom.xml`:** Defines project dependencies, build configurations, and plugins.
- **`src/main/java`:** Contains the source code of the application.
    - **`Main.java`:** Entry point of the application with the console interface.
    - **`Task.java`:** Model class representing a task.
    - **`ITaskRepository.java`:** Interface defining CRUD operations for tasks.
    - **`DBTaskRepository.java`:** Implementation of `ITaskRepository` using H2 Database.
    - **`TaskManager.java`:** Manages task operations by interacting with the repository.

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK):** Java 8 or higher installed on your machine. You can download it from [Oracle](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) or use [OpenJDK](https://openjdk.java.net/install/).
- **Apache Maven:** Installed and configured. Download from the [official website](https://maven.apache.org/download.cgi) and follow the [installation guide](https://maven.apache.org/install.html).
- **Git:** Installed for version control. Download from [here](https://git-scm.com/downloads).

### Installation

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/yourusername/todo-list.git
    cd todo-list
    ```

2. **Verify Maven Installation:**

   Ensure Maven is installed correctly by checking its version:

    ```bash
    mvn -v
    ```

   You should see output indicating the Maven version and Java version.

### Running the Application

1. **Compile the Project:**

   Navigate to the project's root directory (where `pom.xml` is located) and run:

    ```bash
    mvn compile
    ```

   Maven will automatically download the necessary dependencies (including H2 Database) and compile the source code.

2. **Execute the Application:**

   Run the application using Maven's `exec` plugin:

    ```bash
    mvn exec:java
    ```

   Upon successful execution, you will see a welcome message and a console menu to manage your tasks.

    ```
    Welcome to the Todo List Application (H2 Database + Maven)!

    Menu:
    1. Add Task
    2. View All Tasks
    3. Edit Task
    4. Mark Task as Completed
    5. Delete Task
    0. Exit
    Choose an action:
    ```

## OOP Principles

The **Todo List** application exemplifies key **Object-Oriented Programming (OOP)** principles:

1. **Abstraction:**
    - **`Task` Class:** Represents the concept of a task with properties like title, description, status, and creation time.
    - **`ITaskRepository` Interface:** Defines a contract for CRUD operations without specifying implementation details.

2. **Encapsulation:**
    - Class fields are marked as `private` to restrict direct access.
    - Access to class properties is provided through public getter and setter methods.

3. **Polymorphism:**
    - The use of the `ITaskRepository` interface allows for different implementations (e.g., database, in-memory) to be used interchangeably within the `TaskManager`.

4. **Inheritance:**
    - While the current version does not utilize inheritance, the structure allows for easy extension through subclasses if needed in the future.


---

**Enjoy managing your tasks with Todo List!**

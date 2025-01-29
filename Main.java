import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try {
            TodoService todoService = new TodoService();
            UserService userService = new UserService();

            User user1 = new User("Ali Qurbanov", "ali.qurbanov@gmail.com", "password123");
            User user2 = new User("Nurlan Mammadov", "nurlan.mammadov@example.com", "password456");
            userService.addUser(user1);
            userService.addUser(user2);

            Todo todo1 = new Todo("Learn Java SE", "Complete Java course by next month");
            Todo todo2 = new Todo("Teach Java SE", "Training students");
            todoService.addTodo(todo1);
            todoService.addTodo(todo2);

            todoService.bindTodoToUser("ali.qurbanov@gmail.com", todo1.getId());

            todoService.changeStatus(todo1.getId(), true);

            System.out.println("All Users:");
            userService.printAllUsers();
            System.out.println("\nAll Todos:");
            todoService.printAllTodos();

            userService.removeUser("nurlan.mammadov@example.com");
            todoService.removeTodo(todo2.getId());

            System.out.println("\nAll Users:");
            userService.printAllUsers();
            System.out.println("\nAll Todos:");
            todoService.printAllTodos();
        } catch (TodoAlreadyExists e) {
            System.out.println(e.getMessage());
        } catch (TodoNotFound e) {
            System.out.println(e.getMessage());
        } catch (UserAlreadyExists e) {
            System.out.println(e.getMessage());
        } catch (UserNotFound e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
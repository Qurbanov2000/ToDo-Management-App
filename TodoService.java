import java.util.List;
import java.util.UUID;

public class TodoService {

    public void addTodo(Todo newTodo) {
        if (CommonRepo.getTODOS().stream().anyMatch(todo -> todo.getId().equals(newTodo.getId()))) {
            throw new TodoAlreadyExists("Todo already exists!");
        }
        CommonRepo.getTODOS().add(newTodo);
    }
    public void removeTodo(UUID uuid) {
        CommonRepo.getTODOS()
                .stream()
                .filter(u->u.getId().equals(uuid))
                .findFirst()
                .ifPresent(CommonRepo.getTODOS()::remove);
    }

    public void bindTodoToUser(String userEmail, UUID todoUUID) {
        CommonRepo.getUSERS().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(userEmail))
                .findFirst()
                .ifPresentOrElse(
                        user -> CommonRepo.getTODOS().stream()
                                .filter(todo -> todo.getId().equals(todoUUID))
                                .findFirst()
                                .ifPresentOrElse(
                                        todo -> todo.setOwnerEmail(userEmail),
                                        () -> {
                                            throw new TodoNotFound("Todo is not found!");
                                        }
                                ),
                        () -> {
                            throw new UserNotFound("User is not found!");
                        }
                );
    }

    public void changeStatus(UUID uuid, boolean status) {
        CommonRepo.getTODOS().stream()
                .filter(todo -> todo.getId().equals(uuid))
                .findFirst()
                .ifPresentOrElse(
                        todo -> todo.setCompleted(status),
                        () -> {
                            throw new TodoNotFound("Todo is not found!");
                        }
                );
    }

    public void printAllTodos() {
        CommonRepo.getTODOS().forEach(System.out::println);
    }

    public void printAllCompleted() {
        CommonRepo.getTODOS().stream().filter(Todo::isCompleted).forEach(System.out::println);
    }

    public void printAllUnCompleted() {
        CommonRepo.getTODOS().stream().filter(todo -> !todo.isCompleted()).forEach(System.out::println);
    }

    public void printAllCompletedTodosForSpecificUser(String email) {
        CommonRepo.getTODOS().stream()
                .filter(Todo::isCompleted)
                .filter(todo -> todo.getOwnerEmail().equalsIgnoreCase(email))
                .forEach(System.out::println);
    }

    public void printAllUnCompletedTodosForSpecificUser(String email) {
        CommonRepo.getTODOS().stream()
                .filter(todo -> !todo.isCompleted())
                .filter(todo -> todo.getOwnerEmail().equalsIgnoreCase(email))
                .forEach(System.out::println);
    }

    public void printAllUnassignedTodos() {
        CommonRepo.getTODOS().stream().filter(todo -> todo.getOwnerEmail() == null).forEach(System.out::println);
    }

    public void kpiComputingForIndividualUser(String email) {
        User user = CommonRepo.getUSERS().stream().filter(u -> u.getEmail().equals(email)).findFirst()
                .orElseThrow(() -> new UserNotFound("User not found."));

        List<Todo> userTodos = CommonRepo.getTODOS().stream().filter(todo -> email.equals(todo.getOwnerEmail())).toList();
        long completedCount = userTodos.stream().filter(Todo::isCompleted).count();
        long uncompletedCount = userTodos.stream().filter(todo -> !todo.isCompleted()).count();

        System.out.println("User: " + user.getFullName());
        System.out.println("Total Todos: " + userTodos.size());
        System.out.println("Completed Todos: " + completedCount);
        System.out.println("Uncompleted Todos: " + uncompletedCount);

        if (completedCount > uncompletedCount && (completedCount - uncompletedCount) < 5) {
            System.out.println("Status: Suitable for KPI");
        } else {
            System.out.println("Status: Not Suitable for KPI");
        }
    }
}




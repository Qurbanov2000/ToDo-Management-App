import java.util.ArrayList;
import java.util.List;

public class CommonRepo {
    private static final List<User> USERS = new ArrayList<>();
    private static final List<Todo> TODOS = new ArrayList<>();

    public static List<Todo> getTODOS() {
        return TODOS;
    }
    public static List<User> getUSERS() {
        return USERS;
    }
}

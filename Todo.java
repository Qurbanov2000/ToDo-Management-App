import java.util.UUID;

public class Todo {
    private UUID id;
    private String title;
    private boolean completed;
    private String ownerEmail;
    private String description;

    public Todo(String title,String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.completed = false;
        this.ownerEmail = null; // Initially not bound to any user
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

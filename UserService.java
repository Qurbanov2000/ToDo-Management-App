import java.util.stream.Collectors;

public class UserService {
   public void addUser(User newUser) {
       if (CommonRepo.getUSERS().stream().anyMatch(user -> user.getEmail().equals(newUser.getEmail()))) {
           throw new UserAlreadyExists("User already exists!");
       }
       CommonRepo.getUSERS().add(newUser);
   }
    public void removeUser(String email) {
       CommonRepo.getUSERS()
               .stream()
               .filter(u -> u.getEmail().equals(email))
               .findFirst()
               .ifPresentOrElse(
                       CommonRepo.getUSERS()::remove, () ->
                       {throw new UserNotFound("User Not Found"); }
               );
    }
    public void printAllUsers() {
       CommonRepo.getUSERS().forEach(System.out::println);
    }
}

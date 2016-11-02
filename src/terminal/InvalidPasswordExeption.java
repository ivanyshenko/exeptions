package terminal;

public class InvalidPasswordExeption extends Throwable {
    @Override
    public String getMessage() {
        return "Wrong password. Please, verify that the password is correct.";
    }
}

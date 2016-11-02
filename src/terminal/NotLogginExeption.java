package terminal;

public class NotLogginExeption extends Exception {
    @Override
    public String getMessage() {
        return "You are not logged in. Please, log in";
    }
}

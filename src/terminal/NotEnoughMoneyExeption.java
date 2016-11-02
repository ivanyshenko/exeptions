package terminal;

public class NotEnoughMoneyExeption extends Exception {
    @Override
    public String getMessage() {
        return "You have no such money. Please check lesser amount of money.";
    }
}

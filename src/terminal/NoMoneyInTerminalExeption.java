package terminal;

public class NoMoneyInTerminalExeption extends Exception {
    @Override
    public String getMessage() {
        return "This terminal hasn't desired denomination banknotes. Please try another sum";
    }
}

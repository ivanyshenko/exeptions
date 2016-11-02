package terminal;

import java.util.Timer;
import java.util.TimerTask;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PasswordValidator pinValidator;
    private final TerminalScreen screen;

    private Integer id;

    private volatile Boolean isAvailableToLogIn;
    private int logCount;
    private Timer timer;
    private TimerTask timerTask;
    private volatile long delayStart;
    private final long delay = 20000;

    public TerminalImpl() {
        this.server = new TerminalServerImpl();
        this.pinValidator = new PinValidator();
        this.screen = new TerminalScreen();
        this.isAvailableToLogIn = true;
        this.logCount = 0;
        this.timer = new Timer();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                isAvailableToLogIn = false;
                try {
                    delayStart = System.currentTimeMillis();
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    isAvailableToLogIn = true;
                }
            }
        };
    }

    @Override
    public void checkCache() {
        try {
            checkPermition();
        } catch (NotLogginExeption e) {
            screen.print(e.getMessage());
            return;
        }
        screen.print("You have " + server.printCache(id).toString());
    }

    @Override
    public void putToCache(Integer amount) {
        try {
            checkPermition();
        } catch (NotLogginExeption e) {
            screen.print(e.getMessage());
            return;
        }
        server.addToCache(id, amount);
        screen.print("Operation is successful");
    }

    @Override
    public void getCache(Integer amount) {
        try {
            checkPermition();
            verifyAmount(amount);
            server.getCache(id, amount);
            screen.print("Operation is successful");
        } catch (NotLogginExeption e) {
            screen.print(e.getMessage());
            return;
        } catch (NoMoneyInTerminalExeption e) {
            screen.print(e.getMessage());
        } catch (NotEnoughMoneyExeption e) {
            screen.print(e.getMessage());
        }
    }

    @Override
    public void logIn(Integer pass) {
        try {
            tryToLogIn();
            Integer result = pinValidator.checkPass(pass);
            id = result;
            screen.print("You are successfully logged in");
        } catch (AccountIsLockedException e) {
            screen.print(e.getMessage());
            return;
        } catch (InvalidPasswordExeption e) {
            screen.print(e.getMessage());
            logCount++;
            if (logCount > 2){
                this.timer.schedule(timerTask, 0);
                logCount = 0;
            }
        }
    }

    @Override
    public void logOut() {
        id = null;
        screen.print("You are successfully logged out");
    }
    private void tryToLogIn() throws AccountIsLockedException {
        if(!isAvailableToLogIn){
            long currentDelay = delay - System.currentTimeMillis() + delayStart;
            throw new AccountIsLockedException(currentDelay/1000);
        }
    }
    private void checkPermition() throws NotLogginExeption{
        if (id == null){
            throw new NotLogginExeption();
        }
    }
    private void verifyAmount(Integer amount) throws NoMoneyInTerminalExeption{
        if(amount.intValue()%100 != 0){
            throw new NoMoneyInTerminalExeption();
        }
    }
    /* for testing */
    public void run (){
        String commandArr = screen.nextCommand();
        String args[] = commandArr.split(" ");
        switch (args[0]) {
            case "log":
                logIn( new Integer(args[1]));
                break;
            case "exit":
                logOut();
                break;
            case "check":
                checkCache();
                break;
            case "add":
                putToCache(new Integer(args[1]));
                break;
            case "get":
                getCache(new Integer(args[1]));
                break;
            default: screen.print("Wrong command");
        }
    }

    public static void main(String[] args) {
        Terminal terminal = new TerminalImpl();
        while (true){
            terminal.run();
        }
    }
}

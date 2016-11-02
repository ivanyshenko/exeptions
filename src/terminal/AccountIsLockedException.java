package terminal;

public class AccountIsLockedException extends Throwable {
    private long delay;
    public AccountIsLockedException(long delay) {
        super();
        this.delay = delay;
    }

    @Override
    public String getMessage() {
        return "This terminal is blocked for " + delay + " seconds. Please, be patient";
    }
}

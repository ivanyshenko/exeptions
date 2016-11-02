package terminal;

public interface TerminalServer {
    void getCache(Integer id, Integer amount)throws NotEnoughMoneyExeption;
    Integer printCache(Integer id);
    void addToCache(Integer id, Integer amount);
}

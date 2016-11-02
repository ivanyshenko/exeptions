package terminal;

public interface Terminal {
    void checkCache();
    void putToCache(Integer amount);
    void getCache(Integer amount);
    void logIn(Integer pass);
    void logOut();
    void run();
}

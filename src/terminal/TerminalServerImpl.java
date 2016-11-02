package terminal;

import java.util.HashMap;

public class TerminalServerImpl implements TerminalServer {
    private final HashMap<Integer, Integer> clientDB;

    public TerminalServerImpl() {
        this.clientDB = new HashMap<>();
        this.clientDB.put(1, 40000);
        this.clientDB.put(2, 200);
    }

    public void getCache(Integer id, Integer amount)throws NotEnoughMoneyExeption{
        Integer cache = clientDB.get(id);
        Integer result = cache - amount;
        if (result < 0){
            throw new NotEnoughMoneyExeption();
        } else {
            clientDB.replace(id, result);
        }
    }

    public Integer printCache(Integer id){
        return clientDB.get(id);
    }

    public void addToCache(Integer id, Integer amount){
        Integer cache = clientDB.get(id);
        clientDB.replace(id, cache + amount);
    }
}

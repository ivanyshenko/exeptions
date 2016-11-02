package terminal;

import java.util.HashMap;

public class PinValidator implements PasswordValidator {
    private final HashMap<Integer, Integer> clientPasswords;

    public PinValidator() {
        this.clientPasswords = new HashMap<>();
        clientPasswords.put(1234, 1);
        clientPasswords.put(4242, 2);
    }

    public Integer checkPass (Integer pass) throws InvalidPasswordExeption{
        if (clientPasswords.containsKey(pass)){
            return clientPasswords.get(pass);
        }
        else {
            throw new InvalidPasswordExeption();
        }
    }
}

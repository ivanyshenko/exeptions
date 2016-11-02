package terminal;

import java.util.Scanner;

public class TerminalScreen {
    private final Scanner scanner;

    public TerminalScreen() {
        this.scanner = new Scanner(System.in);
    }
    public void print (String string){
        System.out.println(string);
    }
    public String nextCommand(){
        return scanner.nextLine();
    }
}

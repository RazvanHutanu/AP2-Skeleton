import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    HashMap<Identifier,Set> map = new HashMap<>();

    private void start() {
        Scanner in = new Scanner(System.in);

        // Create a scanner on System.in
        interpeter(in);
        // While there is input, read line and parse it.
    }

    public static void main(String[] argv) {
        new Main().start();
    }

    public void interpeter(Scanner in){
        String line = in.nextLine();
        statement(line);
    }

    public void statement(String input) {
        char firstLetter = input.charAt(0);

        if (Character.isLetter(firstLetter)) {
            assignment(input);
        } else if (firstLetter == '?') {
            printStatement(input);
        } else if (firstLetter == '/') {
            //comment
        }
    }

    public void assignment(String input) {
        int i;
        Identifier identifier = makeIdentifier(input);

        i = identifier.getSize();
        char c = input.charAt(i);
        i++;

        while(c != '=' && i < input.length()){
            c = input.charAt(i);
            i++;
        }

        if(i != input.length()){
            expression();
        } else System.out.print("No '=' ");
    }

    public Identifier makeIdentifier(String input){
        int i = 0;
        char c;
        Identifier identifier = new Identifier();

        c = input.charAt(i);
        while(c != ' ' && c != '='){
            if(Character.isLetter(c) || Character.isDigit(c)){
                identifier.add(c);
            }else {
                System.out.print("error: only letters and digits in identifiers");
                break;
            }

            i++;
            c = input.charAt(i);
        }

        return identifier;
    }

    public void expression(){

    }

    public void printStatement(String input){

    }

//    Set factor (Scanner input){
//        char c = nextChar(input);
//
//        if(Character.isLetter(c)){
//            Identifier identifier = new Identifier();
//            identifier.add(c);
//
//            while(Character.isLetter(c) || Character.isDigit(c)){
//                c = nextChar(input);
//                identifier.add(c);
//            }
//            return map.get(identifier);
//        } else
//
//    }

    char nextChar(Scanner in){
        return in.next().charAt(0);
    }
}



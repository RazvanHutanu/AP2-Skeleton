import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    HashMap<Identifier,Set> map = new HashMap<>();

    private void start() {
        Scanner in = new Scanner(System.in).useDelimiter("");

        // Create a scanner on System.in
        parser(in);
        // While there is input, read line and parse it.
    }

    public static void main(String[] argv) {
        new Main().start();
    }

    public void parser(Scanner in){
        String line;
        while(in.hasNextLine()){
            line = in.nextLine();
            Scanner input = new Scanner(line);
            statement(input, line.charAt(0));
        }

    }

    public void statement(Scanner input, char firstLetter) {

        if (Character.isLetter(firstLetter)) {
            assignment(input);
        } else if (firstLetter == '?') {
            printStatement(input);
        } else if (firstLetter == '/') {
            //comment
        }
    }

    public void assignment(Scanner input) {
        Identifier identifier = makeIdentifier(input);

        identifier.print();
        char c = nextChar(input);

        while(c != '=' && input.hasNext()){
            c = nextChar(input);
        }

        if(input.hasNext()){
            expression(input);
        } else System.out.print("No '=' ");
    }

    public Identifier makeIdentifier(Scanner input){
        input.useDelimiter("");
        char c = nextChar(input);
        Identifier identifier = new Identifier();

        while(c != ' ' && c != '='){
            if(Character.isLetter(c) || Character.isDigit(c)){
                identifier.add(c);
            }else {
                System.out.print("error: only letters and digits in identifiers");
                break;
            }

            c = nextChar(input);
        }

        return identifier;
    }

    Set expression(Scanner input){
        Set product = new Set();
        product = term(input);

        while (input.hasNext()){

        }

    }

    Set term(Scanner input){

    }

    public void printStatement(Scanner input){

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



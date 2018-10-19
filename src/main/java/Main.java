import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
@SuppressWarnings("unchecked")
public class Main {
    private char currentChar;
    private int parenthesisCounter;

    HashMap<StringBuffer,Set<BigInteger>> map = new HashMap<>();

    private void start() throws APException{
        Scanner in = new Scanner(System.in);

        // Create a scanner on System.in
        parser(in);
        // While there is input, read line and parse it.
    }

    public static void main(String[] argv) {
        try {
            new Main().start();
        }catch(APException e){
            System.out.print(e);
        }
    }

    public void parser(Scanner in) throws APException{
        String line;
        while(in.hasNextLine()){
            parenthesisCounter = 0;
            line = in.nextLine();
            Scanner input = new Scanner(line);
            input.useDelimiter("");
            currentChar = line.charAt(0);
            statement(input);
        }

    }

    public void statement(Scanner input) throws APException{

        if (Character.isLetter(currentChar)) {
            assignment(input);
        } else if (currentChar == '?') {
            printStatement(input);
        } else if (currentChar == '/') {
            comment(input);
        }
    }

    public void printStatement(Scanner input) throws APException{
        System.out.println("current char: " + currentChar);
        nextChar(input);
        nextChar(input);
        System.out.println("current char: " + currentChar);
        System.out.println(expression(input));
    }

    public void assignment(Scanner input) throws APException{
        Identifier identifier = makeIdentifier(input);

        removeWhiteSpaces(input);


        if(currentChar == '=' || input.hasNext()){
            nextChar(input);
            System.out.print("current char in assignment dupa egal: " + currentChar);
            identifier.print();
            map.put(identifier.getIdentifier(), expression(input));
        } else throw new APException("No '=' ");

//        if(input.hasNext() || currentChar == '='){
//            expression(input);
//        } else System.out.print("No '=' ");

    }

    public Identifier makeIdentifier(Scanner input) throws APException{
        Identifier identifier = new Identifier();

//        System.out.println("current char in makeIdentifier: " + currentChar);
        do{
            if(Character.isLetter(currentChar) || Character.isDigit(currentChar)){
//                System.out.println("caracterul adaugat in identifier: " + currentChar);
                identifier.add(currentChar);
            }else {
                throw new APException("only letters and digits in identifiers");
            }

            nextChar(input);
            nextChar(input);
//            System.out.println("current char dupa 2 next char in makeidentifier " + currentChar);
        }while(currentChar != ' ' && currentChar != '=' && input.hasNext());
        System.out.print("Identifierul format in makeIdentifier: ");
        identifier.print();
        return identifier;
    }


    Set expression(Scanner input) throws APException{
        removeWhiteSpaces(input);
        Set<BigInteger> product = term(input);
//        System.out.print(currentChar + "\n");


//        System.out.println("current char dupa term in expression: " + currentChar);
        while (input.hasNext() && currentChar != '\n'){
//            System.out.print("Expression while\n");
            removeWhiteSpaces(input);
            switch(currentChar){
                case '+': {
//                    System.out.println("Am intrat in +");
                    nextChar(input);
                    removeWhiteSpaces(input);
                    product = (Set<BigInteger>) product.union(term(input));
                    break;
                }

                case '-':{
                    nextChar(input);
                    removeWhiteSpaces(input);
                    product = (Set<BigInteger>) product.difference(term(input));
                    break;
                }

                case '|':{
                    nextChar(input);
                    removeWhiteSpaces(input);
                    product = (Set<BigInteger>) product.symmetricDifference(term(input));
                    break;
                }
                case ')':{
                    if(parenthesisCounter == 0)
                        throw new APException("Invalid character");

                    parenthesisCounter--;
                    return product;
                }
                default:{
                    throw new APException("Invalid operator: " + currentChar);
                }
            }
        }
        return product;
    }


    Set term(Scanner input) throws APException{
        removeWhiteSpaces(input);
         Set<BigInteger> product = factor(input);

        removeWhiteSpaces(input);

//        System.out.println(product.toString());
//        System.out.println("current char in term: " + currentChar);
//        while(input.hasNext() && currentChar != '\n'){
            removeWhiteSpaces(input);
            if( currentChar == '*'){
                nextChar(input);
                removeWhiteSpaces(input);
                product = (Set<BigInteger>) product.intersection(term(input));
            }
//        }

        return product;
    }



    Set factor (Scanner input) throws APException{
        removeWhiteSpaces(input);

        if(Character.isLetter(currentChar)){
            System.out.println("factor current char: " + currentChar);
            Identifier identifier = makeIdentifier(input);
//            identifier.print();
            return map.get(getSet(identifier));
        }
        else if (currentChar == '{'){
            nextChar(input);
            return rowNaturalNumber(input);
        } else if(currentChar == '('){
            return complexFactor(input);
        }

        throw new APException("Invalid character");
    }

    //ia un numer cu getNumber, verifica currentChar daca e virgula
    Set rowNaturalNumber(Scanner input) throws APException{
        Set<BigInteger> set = new Set<>();
        BigInteger number;

        while(currentChar != '}' && input.hasNext()){
            removeWhiteSpaces(input);

            if(Character.isDigit(currentChar)) {
                number = getNumber(input);
                removeWhiteSpaces(input);

                if(currentChar == ',') {
//                    System.out.println("s-o adaugat in lista: " + number);
                    set.add(number);
                }else if(currentChar == '}') {
//                    System.out.println("s-o adaugat in lista inainte de }: " + number);
                    set.add(number);
                    nextChar(input);
                    break;
                }
                else
                    throw new APException("Invalid character");
            }
            nextChar(input);
        }

        return set;
    }

    BigInteger getNumber(Scanner input) throws APException{
        String number = "";

        if(currentChar != '0') {
            while (Character.isDigit(currentChar)) {
                number += currentChar;
                nextChar(input);
            }
        } else{
            number += currentChar;
            nextChar(input);
        }

        return new BigInteger(number);
    }

    Set complexFactor(Scanner input)throws APException{
        parenthesisCounter++;
        nextChar(input);
        Set<BigInteger> set = expression(input);

        if(currentChar != ')')
            throw new APException("no closing parenthesis");

        nextChar(input);
        return set;
    }

    public void comment(Scanner input){
        if(input.hasNextLine())
            input.nextLine();
    }

<<<<<<< Updated upstream
    Set getSet(Identifier identifier) throws APException {
        System.out.println("Identifierul cautat in map: " + identifier.getIdentifier());
        if(map.get(identifier.getIdentifier()) == null)
=======
    Set getSet(StringBuffer identifier) throws APException {
       // System.out.println("Identifierul cautat in map: " + identifier);
        if(map.get(identifier) == null)
>>>>>>> Stashed changes
            throw new APException("Set not found");

        return map.get(identifier.getIdentifier());
    }

    char nextChar(Scanner input){
        if(input.hasNext()) {
            currentChar = input.next().charAt(0);
        }
        else
            currentChar = '\n';
        return currentChar;
    }

    void removeWhiteSpaces(Scanner input){
        while(currentChar == ' ' && input.hasNext())
            currentChar = nextChar(input);

    }
}



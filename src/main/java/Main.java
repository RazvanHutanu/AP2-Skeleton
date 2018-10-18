import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
@SuppressWarnings("unchecked")
public class Main {
    char currentChar;
    int parenthesisCounter;

    HashMap<Identifier,Set> map = new HashMap<>();

    private void start() throws APException{
        Scanner in = new Scanner(System.in).useDelimiter("");

        // Create a scanner on System.in
        parser(in);
        // While there is input, read line and parse it.
    }

    public static void main(String[] argv) {
        try {
            new Main().start();
        }catch(APException e){
            System.out.print("error: " + e);
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
        System.out.println("current char: " + currentChar);
        System.out.println(expression(input));
    }

    public void assignment(Scanner input) throws APException{
        Identifier identifier = makeIdentifier(input);

        identifier.print();

        removeWhiteSpaces(input);

        if(currentChar == '='){
            nextChar(input);
            map.put(identifier, expression(input));
        } else throw new APException("No '=' ");
    }

    public Identifier makeIdentifier(Scanner input) throws APException{
        input.useDelimiter("");
        Identifier identifier = new Identifier();

        nextChar(input);
        while(currentChar != ' ' && currentChar != '='){
            if(Character.isLetter(currentChar) || Character.isDigit(currentChar)){
                identifier.add(currentChar);
            }else {
                throw new APException("only letters and digits in identifiers");
            }

            nextChar(input);
        }
        return identifier;
    }

    Set expression(Scanner input) throws APException{
        Set<BigInteger> product = term(input);
        System.out.print(currentChar + "\n");
        removeWhiteSpaces(input);

        while (input.hasNext() && currentChar != '\n'){
            System.out.print("Expression while\n");
            removeWhiteSpaces(input);
            switch(currentChar){
                case '+': {
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

        while(input.hasNext() && currentChar != '\n'){
            removeWhiteSpaces(input);
            if( currentChar == '*'){
                nextChar(input);
                removeWhiteSpaces(input);
                product = (Set<BigInteger>) product.intersection(term(input));
            }
        }

        return product;
    }



    Set factor (Scanner input) throws APException{
        removeWhiteSpaces(input);
        System.out.println("factor current char: " + currentChar);

        if(Character.isLetter(currentChar)){
            return map.get(getSet(makeIdentifier(input)));
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
                    set.add(number);
                }else if(currentChar == '}') {
                    set.add(number);
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

    Set getSet(Identifier identifier) throws APException {
        if(map.get(identifier) == null)
            throw new APException("Set not found");

        return map.get(identifier);
    }

    char nextChar(Scanner input){
        if(input.hasNext()) {
            String tmp = input.next();
            System.out.println("TEMP: " + tmp);
            currentChar = tmp.charAt(0);
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



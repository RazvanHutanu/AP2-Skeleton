
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
@SuppressWarnings("unchecked")
public class Main {
    private char currentChar;
    private int parenthesisCounter;

    HashMap<Identifier,Set<BigInteger>> map = new HashMap<>();

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
            System.out.println(e.getMessage());
        }
    }

    public void parser(Scanner in){
        String line;
        while(in.hasNextLine()){
            parenthesisCounter = 0;
            line = in.nextLine();
            line += " ";
            Scanner input = new Scanner(line);
            input.useDelimiter("");
            nextChar(input);
            try {
                removeWhiteSpaces(input);
                statement(input);
            }catch(APException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void statement(Scanner input) throws APException{

        if (Character.isLetter(currentChar)) {
            assignment(input);
        } else if (currentChar == '?') {
            printStatement(input);
        } else if (currentChar == '/') {
            comment(input);
        } else throw new APException("No statement");
    }

    public void printStatement(Scanner input) throws APException{
        nextChar(input);
        System.out.println(expression(input));
    }

    public void assignment(Scanner input) throws APException{
        Identifier identifier = makeIdentifier(input);

        removeWhiteSpaces(input);

        if(currentChar == '=' && input.hasNext()){
            nextChar(input);
            map.put(identifier, expression(input));
        } else throw new APException("No '=' ");

    }

    public Identifier makeIdentifier(Scanner input) throws APException{
        Identifier identifier = new Identifier();
        identifier.add(currentChar);
        nextChar(input);

        while(Character.isLetter(currentChar) || Character.isDigit(currentChar)){
            identifier.add(currentChar);
            nextChar(input);
        }
        return identifier;
    }


    Set expression(Scanner input) throws APException{
        removeWhiteSpaces(input);
        Set<BigInteger> product = term(input);

        while (input.hasNext() && currentChar != '\n'){
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
            } else break;
        }

        return product;
    }



    Set factor (Scanner input) throws APException{
        removeWhiteSpaces(input);

        if(Character.isLetter(currentChar)){
            return getSet(makeIdentifier(input));
        }
        else if (currentChar == '{'){
            nextChar(input);
            return rowNaturalNumber(input);
        } else if(currentChar == '('){
            return complexFactor(input);
        }

        throw new APException("Invalid character");
    }


    Set rowNaturalNumber(Scanner input) throws APException{
        Set<BigInteger> set = new Set<>();
        BigInteger number;
        boolean changeChar;

        removeWhiteSpaces(input);
        while(currentChar != '}' && input.hasNext()){
            changeChar = true;
            removeWhiteSpaces(input);
            if(Character.isDigit(currentChar)) {
                number = getNumber(input);
                removeWhiteSpaces(input);

                if(currentChar == ',') {
                    set.add(number);

                    nextChar(input);
                    changeChar = false;
                    removeWhiteSpaces(input);
                    if(currentChar == '}')
                        throw new APException("No character after comma");

                }else if(currentChar == '}') {
                    set.add(number);
//                    nextChar(input);
                    break;
                }
                else
                    throw new APException("Invalid character");

            }else throw new APException("Invalid character");
            if(changeChar)
                nextChar(input);
        }

        if(currentChar == '}')
            nextChar(input);
        else
            throw new APException("No closing bracket");
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
            currentChar = input.next().charAt(0);
        }
        else
            currentChar = '\n';
        return currentChar;
    }

    void removeWhiteSpaces(Scanner input){
        while(currentChar == ' ' && input.hasNext())
            nextChar(input);
    }
}



import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // get input
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter expression to evaluate: ");
        String input  = reader.nextLine();
        reader.close();

        // lexer
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.scanTokens();

//        for (Token t : tokens){
//            System.out.println(t);
//        }

        //parsing
        SYParser parser = new SYParser(tokens);
        try {
            parser.parse();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        // declared ahead of time because of scoping in try block
        double value;
        // evaluation
        try {
            value = parser.evaluate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println( "= " + value);
    }
}
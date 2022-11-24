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

        for (Token t : tokens){
            System.out.println(t);
        }

        //parsing
        SYParser parser = new SYParser(tokens);

        parser.parse();
        if (parser.hadError()){
            System.out.println(parser.errorMsg());
            return;
        }

        // evaluate
        double value = parser.evaluate();
        if (parser.hadError()){
            System.out.println(parser.errorMsg());
            return;
        }

        System.out.println( "= " + value);
    }
}
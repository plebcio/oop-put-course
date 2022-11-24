import javax.xml.validation.Validator;
import java.util.*;
import java.lang.Math;

// parser and evaluator implemented using the Shunning Yard Algorithm
public class SYParser implements Parser {

    final private List<Token> infix;
    final private List<Token> postfix;

    final private Set<String> functions = new HashSet<>();

    public SYParser(List<Token> tokens){
        infix = tokens;
        postfix = new ArrayList<>();

        functions.add("sin");
        functions.add("cos");
        functions.add("ln");
        functions.add("exp");
    }

    @Override
    public void parse() throws Exception {
        // initialise stack for operators
        Stack<Token> tokenStack = new Stack<>();

        // Shunning yard parsing algorithm
        for (int i = 0; i < infix.toArray().length; i++){
            Token currToken = infix.get(i);
            switch (currToken.type()) {
                case NUM -> postfix.add(currToken);
                case L_PAREN -> tokenStack.push(currToken);
                case R_PAREN -> {
                    // pop elements of the stack until '(' is found
                    while (!tokenStack.isEmpty() && tokenStack.peek().type() != TokenType.L_PAREN) {
                        // add popped element to output list
                        postfix.add(tokenStack.pop());
                    }
                    tokenStack.pop(); // pop the '('
                }

                // handle binary operators and functions
                default -> {
                    while (!tokenStack.isEmpty()
                            && getPrecedence(currToken.type()) <= getPrecedence(tokenStack.peek().type())
                            && isLeftAssoviative(currToken.type())) {
                        postfix.add(tokenStack.pop());
                    }
                    tokenStack.push(currToken);
                }
            }
        }
        // pop the rest of the stack into postfix list
        while (!tokenStack.isEmpty()) {
            if (tokenStack.peek().type() == TokenType.L_PAREN) {
                throw new Exception("Syntax error: Missing '('. ");
            }
            postfix.add(tokenStack.pop());
        }
    }
    
    @Override
    public double evaluate() throws Exception {
        Stack<Double> valueStack = new Stack<>();

        for (int i = 0; i < postfix.toArray().length; i++){
            Token currToken = postfix.get(i);
            switch (currToken.type()){
                case NEG -> {
                    double a = valueStack.pop();
                    valueStack.push(-1*a);
                }
                case NUM -> {
                    try
                    {
                        valueStack.push(
                                Double.parseDouble(currToken.value())
                        );
                    }
                    catch(NumberFormatException e)
                    {
                        throw new Exception("Syntax error: Expected number but got " + currToken.value());
                    }
                }
                case ADD -> {
                    if (valueStack.size() < 2) {
                        throw new Exception("Syntax error: Missing operand(s) for '+' ");
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    valueStack.push(a+b);
                }
                case SUB -> {
                    if (valueStack.size() < 2) {
                        throw new Exception("Syntax error: Missing operand(s) for '-' ");
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    valueStack.push(a-b);
                }
                case MUL -> {
                    if (valueStack.size() < 2) {
                        throw new Exception("Syntax error: Missing operand(s) for '*' ");
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    valueStack.push(a*b);
                }
                case DIV -> {
                    if (valueStack.size() < 2) {
                        throw new Exception("Syntax error: Missing operand(s) for '/' ");
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();

                    if (b == 0){
                        throw new Exception("Math error: Division by 0");
                    }

                    valueStack.push(a/b);
                }
                case EXP -> {
                    if (valueStack.size() < 2) {
                        throw new Exception("Syntax error: Missing operand(s) for '^' ");
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    valueStack.push(Math.pow(a, b));
                }
                case FUNC -> {
                    if (functions.contains(currToken.value())){

                        if (valueStack.size() < 1) {
                            throw new Exception("Syntax error: Missing operand for func " + currToken.value());
                        }

                        double a = valueStack.pop();
                        switch (currToken.value()) {
                            case "sin" -> valueStack.push(Math.sin(a));
                            case "cos" -> valueStack.push(Math.cos(a));
                            case "ln" -> valueStack.push(Math.log(a));
                            case "exp" -> valueStack.push(Math.exp(a));
                        }
                        // check for illegal arguments eg ln(-7) returns NaN
                        if (Double.isNaN(valueStack.peek())){
                            throw new Exception("Math error: Illegal argument for '" + currToken.value() + "'");
                        }
                    }
                    else {
                        throw new Exception("Syntax error: Unknown symbol " + currToken.value());
                    }
                }
            }
        }
        return valueStack.pop();
    }

    private boolean isLeftAssoviative(TokenType type){
        return type == TokenType.ADD || type == TokenType.SUB
                || type == TokenType.MUL || type == TokenType.DIV;

        // the right associative types are NEG, FUNC (eg ln sin exp x -> ln(sin(exp(x))), and EXP
    }

    private int getPrecedence(TokenType type){
        return switch (type) {
            case ADD, SUB -> 1;
            case DIV, MUL -> 2;
            case EXP -> 3;
            case FUNC -> 4;
            case NEG -> 5;
            default -> -1; // shouldn't happen
        };
    }
}

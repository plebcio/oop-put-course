import java.util.*;
import java.lang.Math;

// parser and evaluator implemented using the Shunning Yard Algorithm
public class SYParser implements Parser{

    public boolean hadError;
    private String errorStr = "";
    final private List<Token> infix;
    final private List<Token> postfix;
    final private Stack<Token> stack;

    final private Set<String> functions = new HashSet<>();

    public SYParser(List<Token> tokens){
        infix = tokens;
        postfix = new ArrayList<>();
        stack = new Stack<>();
        hadError = false;

        functions.add("sin");
        functions.add("cos");
        functions.add("ln");
        functions.add("exp");
    }

    @Override
    public void parse(){
        for (int i = 0; i < infix.toArray().length; i++){
            Token currToken = infix.get(i);
            switch (currToken.type()) {
                case NUM -> postfix.add(currToken);
                case L_PAREN -> stack.push(currToken);
                case R_PAREN -> {
                    // pop elements of the stack until '(' is found
                    while (!stack.isEmpty() && stack.peek().type() != TokenType.L_PAREN) {
                        // add popped element to output list
                        postfix.add(stack.pop());
                    }
                    stack.pop(); // pop the '('
                }

                // handle binary operators and functions
                default -> {
                    while (!stack.isEmpty()
                            && getPrecedence(currToken.type()) <= getPrecedence(stack.peek().type())
                            && isLeftAssoviative(currToken.type())) {
                        postfix.add(stack.pop());
                    }
                    stack.push(currToken);
                }
            }
        }
        // pop the rest of the stack into postfix
        while (!stack.isEmpty()) {
            if (stack.peek().type() == TokenType.L_PAREN) {
                hadError = true;
                errorStr = "Mismatching parenthesis";
                return;
            }
            postfix.add(stack.pop());
        }
    }
    
    @Override
    public double evaluate(){
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
                        setError("Expected number but got " + currToken.value());
                        return Double.NaN;
                    }
                }
                case ADD -> {
                    if (valueStack.size() < 2) {
                        setError("Missing operand(s) for '+' ");
                        return Double.NaN;
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    valueStack.push(a+b);
                }
                case SUB -> {
                    if (valueStack.size() < 2) {
                        setError("Missing operand(s) for '-' ");
                        return Double.NaN;
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    valueStack.push(a-b);
                }
                case MUL -> {
                    if (valueStack.size() < 2) {
                        setError("Missing operand(s) for '*' ");
                        return Double.NaN;
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    valueStack.push(a*b);
                }
                case DIV -> {
                    if (valueStack.size() < 2) {
                        setError("Missing operand(s) for '/' ");
                        return Double.NaN;
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();

                    if (b == 0){
                        setError("Tried to divide by 0");
                        return Double.NaN;
                    }

                    valueStack.push(a/b);
                }
                case EXP -> {
                    if (valueStack.size() < 2) {
                        setError("Missing operand(s) for '^' ");
                        return Double.NaN;
                    }
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    valueStack.push(Math.pow(a, b));
                }
                case FUNC -> {
                    if (functions.contains(currToken.value())){

                        if (valueStack.size() < 1) {
                            setError("Missing operand for func " + currToken.value());
                            return Double.NaN;
                        }

                        double a = valueStack.pop();
                        switch (currToken.value()) {
                            case "sin" -> valueStack.push(Math.sin(a));
                            case "cos" -> valueStack.push(Math.cos(a));
                            case "ln" -> valueStack.push(Math.log(a));
                            case "exp" -> valueStack.push(Math.exp(a));
                        }
                    }
                    else {
                        setError("Unknown symbol " + currToken.value());
                        return Double.NaN;
                    }
                }
            }
        }
        return valueStack.pop();
    }

    @Override
    public boolean hadError(){
        return hadError;
    }

    @Override
    public String errorMsg(){
        return errorStr;
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

    private void setError(String Msg){
        this.hadError = true;
        errorStr = Msg;
    }
}

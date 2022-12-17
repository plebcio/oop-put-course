package pl.poznan.put.calculator;

import java.util.ArrayList;
import java.util.List;

public class SYCalculator implements Calculator{
    private final ArrayList<String> inputHistory;
    private double lastAnswer;

    @Override
    public double expressionValue(String inputString) throws Exception {
        inputHistory.add(inputString);

        Lexer lexer = new Lexer(inputString);
        List<Token> tokenList = lexer.scanTokens();

        SYExpression expression = new SYExpression(tokenList, lastAnswer);

        try {
            expression.parse();
        }
        catch (Exception ex){
            throw ex;
        }

        double expression_value;
        try {
            expression_value = expression.evaluate();
        }
        catch (Exception ex){
            throw ex;
        }

        lastAnswer = expression_value;
        return expression_value;

    }

    @Override
    public ArrayList<String> expressionHistory(){
        return inputHistory;
    }

    public SYCalculator(){
        inputHistory = new ArrayList<>();
        lastAnswer = 0;
    }
}

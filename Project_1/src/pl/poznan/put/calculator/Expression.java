package pl.poznan.put.calculator;

public interface Expression {
    public double evaluate() throws Exception;
    public void parse() throws Exception;
}
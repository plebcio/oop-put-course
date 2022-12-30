package pl.poznan.put.calculator;

import java.util.ArrayList;

public interface Calculator {
    double expressionValue(String inputString) throws Exception;
    ArrayList<String> expressionHistory();
}

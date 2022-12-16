package pl.poznan.put.calculator;

import java.util.ArrayList;
import java.util.List;

public class Lexer implements LexerInterface {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;

    public Lexer(String source){
        this.source = source;
    }

    @Override
    public List<Token> scanTokens(){
        while(!isAtEnd()){
            start = current;
            scanToken();
        }
        return tokens;
    }


    private void scanToken(){
        char c = advance();
        switch (c) {
            case ' ' : break;
            case '-' : {
            // if the right operand ( last token) evaluates to a number, emit SUB token
            // else emit NEG token
                Token lastTok = lastToken();
                if (lastTok.type() == TokenType.NUM || lastTok.type() == TokenType.FUNC)
                    addToken(TokenType.SUB);
                else {
                    addToken(TokenType.NEG);
                }
                break;
            }
            case '+' : addToken(TokenType.ADD); break;
            case '*' : addToken(TokenType.MUL); break;
            case '/' : addToken(TokenType.DIV); break;
            case '^' : addToken(TokenType.EXP); break;
            case '(' : addToken(TokenType.L_PAREN); break;
            case ')' : addToken(TokenType.R_PAREN); break;

            // numbers   and functions
            default:
                if (Character.isDigit(c)){
                    number();
                }
                else {
                    identifier();
                }
        }
    }

    private void addToken(TokenType type){
        tokens.add( new Token(type, source.substring(start, current)));
    }

    private Token lastToken(){
        if (tokens.size() == 0) return new Token(TokenType.NONE, "");
        return tokens.get(tokens.size()-1);
    }

    private void number(){
        while(Character.isDigit(peek())){
            advance();
        }
        // allows for dots
        if (peek() == '.'){
            advance();
        }

        while(Character.isDigit(peek())) {
            advance();
        }

        addToken(TokenType.NUM);
    }

    private void identifier(){
        while (Character.isLetter(peek())){
            advance();
        }
        // special case for non-function identifiers
        String identifier = source.substring(start, current);
        switch (identifier) {
            case "ans" -> addToken(TokenType.ANS);
            case "e", "pi" -> addToken(TokenType.CONST);
            case "$" -> addToken(TokenType.ARG);
            // error catching left to runtime evaluator
            default -> addToken(TokenType.FUNC);
        }
    }

    private char peek(){
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private boolean isAtEnd(){
        return current >= source.length();
    }

    private char advance(){
        current++;
        return source.charAt(current - 1);
    }

}

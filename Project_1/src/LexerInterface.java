import java.util.List;

public interface LexerInterface {
    //public  LexerInterface(String s); no constructors in interfaces ?
    public List<Token> scanTokens();
}

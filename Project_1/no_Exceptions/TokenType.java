public enum TokenType {
    NONE, // helper for lack of token
    NEG, // unary negation
    MUL,
    ADD,
    DIV,
    EXP,
    SUB,
    L_PAREN,
    R_PAREN,
    FUNC,
    NUM;

    @Override
    public String toString() {
        return this.name();
    }
}

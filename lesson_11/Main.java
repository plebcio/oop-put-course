public class Main {
    public static void main(String[] args) {
        LowerCased txt = new LowerCased(
            new Concatination(
                    new Characters("HelLO "),
                    new Subsequence( new Characters("Worldeeeeee"),0, 5)
            )
        );

        System.out.println(txt.printedSeq());
    }
}
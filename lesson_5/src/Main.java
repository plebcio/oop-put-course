public class Main {
    public static void main(String[] args) {

        Euro konto = new Euro(0);
        konto = konto.addedCurrency(1, "JPY");
        System.out.println(konto.balance());
        konto = konto.addedCurrency(55, "CHF");
        System.out.println(konto.balance());


    }
}
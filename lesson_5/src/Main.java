public class Main {
    public static void main(String[] args) {

        Euro konto = new Euro(0);
        konto = konto.addedCurrency(1, "JPY");
        System.out.println(konto.balance() + konto.symbol());

        konto = konto.addedCurrency(50, "USD");
        System.out.println(konto.balance() + konto.symbol());

        konto = konto.subtractedCurrency(50, "USD");
        System.out.println(konto.balance() + konto.symbol());




    }
}
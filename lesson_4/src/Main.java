public class Main {
    public static void main(String[] args) {
        Square a = new Square(4);
        System.out.println(
                a.cirumference()
        );

        Square b = a.scaleX(4);
        System.out.println(
                b.cirumference()
        );
    }
}
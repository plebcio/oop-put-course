public class Main {
    public static void main(String[] args) {
        NumberPair p1 = new NumberPair(1,5);
        System.out.println(p1.max());
        System.out.println(p1.min());
        System.out.println(p1.avg());

        Logarithm loger = new Logarithm(3, 9);
        try {
            System.out.println(loger.doubleValue());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
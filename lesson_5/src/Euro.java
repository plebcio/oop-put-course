public class Euro implements Currency {

    private final float ballance;
    private final FakeCantor kantor = new FakeCantor();

    public Euro addedCurrency(float value, String currency){
        float in_euro = ballance + value/ kantor.euroToRate(currency);
        return new Euro(in_euro);
    }
    public Currency subtractedCurrency(float value, String currency){
        float in_euro = ballance - value/ kantor.euroToRate(currency);
        return new Euro(in_euro);
    }
    public String abbreviation(){
        return "EUR";
    }
    public  String symbol(){
        return "€";
    }
    public String balance(){
        return Float.toString(ballance);
    }
    public float toDollarExchangeRate(){
        return kantor.euroToRate("USD");
    }

    Euro(float ballance) {
        this.ballance = ballance;
    }

}

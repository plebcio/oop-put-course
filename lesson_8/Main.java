public class Main {
    public static void main(String[] args) {
        Forecast myFakeForecast = new Forecast.FakeForcast();
        Weather weather = new Weather(myFakeForecast, 'C');

        String expectedString = "Temperature: 21,70, Humidity: 0,50";
        assert expectedString.equals( weather.weatherToday());
        //System.out.println(weather.weatherToday());
    }
}
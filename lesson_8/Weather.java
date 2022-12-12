public class Weather {
    final private Forecast forecast;
    final private char defaultUnit;

    public String weatherToday() {
        try {
            String temperature = String.format("%.2f", forecast.temperatureToday(defaultUnit));
            String humidity = String.format("%.2f", 0.5f);
            return "Temperature: " + temperature + ", " + "Humidity: " + humidity;
        } catch (Exception ex){
            return ex.getMessage();
        }
    }

    public Weather(Forecast forecast, char unit){
        defaultUnit = unit;
        this.forecast = forecast;
    }
}

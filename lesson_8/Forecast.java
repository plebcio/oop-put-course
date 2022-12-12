public interface Forecast {
    float temperatureToday(char unit) throws Exception;
    final class FakeForcast implements Forecast{

        @Override
        public float temperatureToday(char unit) throws Exception {
            switch(Character.toLowerCase(unit)){
                case 'f' -> {
                    return 55.4f;
                }
                case 'c' -> {
                    return 21.7f;
                }
                case 'k' -> {
                    return 294.7f;
                }
                default -> {
                    throw new Exception("Incorrect Unit: " + unit);
                }
            }
        }
    }
}

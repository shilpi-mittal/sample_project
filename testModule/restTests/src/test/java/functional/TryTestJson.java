package functional;

public class TryTestJson {
    public static String getTryTestJson() {
        return "{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}";
    }

    public static String getTryTestJson(int salary) {
        return "{\"name\":\"test\",\"salary\":\"" + salary + "\",\"age\":\"23\"}";
    }
}

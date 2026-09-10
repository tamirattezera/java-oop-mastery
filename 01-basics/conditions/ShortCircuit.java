package conditions;

public class ShortCircuit {

    public static void main(String[] args) {

        int age = 15;

        boolean result = age >= 18 && checkId();

        System.out.println(result);
    }

    static boolean checkId() {
        System.out.println("Checking ID...");
        return true;
    }
}
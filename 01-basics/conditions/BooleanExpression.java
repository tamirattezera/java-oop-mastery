package conditions;

public class BooleanExpression {

    public static void main(String[] args) {

        int age = 22;

        boolean isAdult = age >= 18;

        System.out.println(isAdult);

        if (isAdult) {
            System.out.println("Adult");
        }
    }
}
package conditions;

public class BooleanLogic {

    public static void main(String[] args) {

        int age = 22;
        boolean hasId = true;
        boolean isStudent = true;

        boolean canEnter = age >= 18 && hasId;
        boolean getsDiscount = isStudent || age >= 60;

        System.out.println(canEnter);
        System.out.println(getsDiscount);
    }
}
package conditions;

public class AccessDecision {

    public static void main(String[] args) {

        int age = 22;
        boolean hasId = true;
        boolean isBanned = false;

        boolean canEnter =
                age >= 18
                && hasId
                && !isBanned;

        System.out.println(canEnter);
    }
}
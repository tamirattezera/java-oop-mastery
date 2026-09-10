package conditions;

public class ShortCircuitOr {

    public static void main(String[] args) {

        boolean isAdmin = true;

        boolean canAccess = isAdmin || checkPermission();

        System.out.println(canAccess);
    }

    static boolean checkPermission() {
        System.out.println("Checking permission...");
        return true;
    }
}
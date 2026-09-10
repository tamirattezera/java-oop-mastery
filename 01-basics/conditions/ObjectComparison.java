package conditions;

public class ObjectComparison {

    public static void main(String[] args) {

        String a = new String("Java");
        String b = new String("Java");

        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
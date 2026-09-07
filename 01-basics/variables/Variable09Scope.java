package variables;

public class Variable09Scope {

public static void main(String[] args) {

    int age = 22;

    if (age >= 18) {

        int status = 1;

        System.out.println(status);
    }

    System.out.println(age);

    // System.out.println(status);
}

}

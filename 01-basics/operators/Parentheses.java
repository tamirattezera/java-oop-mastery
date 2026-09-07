package operators;

public class Parentheses {

    public static void main(String[] args) {

        System.out.println(5 + 3 * 2);
        System.out.println((5 + 3) * 2);
        System.out.println(((10 + 5) * 2) - 4);

    }
}

/*1. Parentheses first
2. * / % next
3. + - last

And when operators have equal precedence:

left → right
*/
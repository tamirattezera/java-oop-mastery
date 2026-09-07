package operators;

public class StringConcatenation {

    public static void main(String[] args) {

        // Addition happens first because both operands are numbers.
        // Then the result is concatenated with the String.
        // Output: 30 Java
        System.out.println(10 + 20 + " Java");

        // Evaluation happens from left to right.
        // Once a String is encountered, + performs concatenation.
        // Output: Java 1020
        System.out.println("Java " + 10 + 20);

        // The String appears first, so the following + operations
        // concatenate the values instead of performing numeric addition.
        // Output: Result: 1020
        System.out.println("Result: " + 10 + 20);

        // Parentheses force numeric addition to happen first.
        // Output: Result: 30
        System.out.println("Result: " + (10 + 20));
    }
}
# Java Operators

## 1. Overview

Operators are symbols that tell Java to perform an operation on one or more values.

Variables allow a program to hold and reference data. Operators allow the program to **work with that data**.

For example:

```java
int price = 100;
int quantity = 3;

int total = price * quantity;
```

Here:

```text
price     → 100
quantity  → 3
   │          │
   └──── * ───┘
         ↓
       total
         ↓
        300
```

Operators are fundamental to calculations, comparisons, decisions, state changes, and expressions throughout Java programs.

---

## 2. Why This Concept Exists

Variables alone cannot perform useful computation.

A program needs to calculate, compare, combine, and modify values.

For example, an e-commerce application may need to calculate:

```java
double total = price * quantity;
```

A login system may need to compare values:

```java
password.equals(inputPassword)
```

A business rule may need to determine:

```java
age >= 18
```

A program may need to update state:

```java
quantity--;
```

Operators provide the language-level mechanism for these operations.

The engineering problem they solve is:

> **How can a program transform, compare, combine, and manipulate values?**

---

## 3. Core Idea

An operator operates on one or more **operands**.

Example:

```java
price + quantity
```

Conceptually:

```text
operand      operator      operand
   │             │             │
 price           +          quantity
   │                           │
  100                           3
               ↓
             result
               ↓
              103
```

### Operator

The symbol that specifies the operation:

```java
+
-
*
/
%
```

### Operand

A value or expression that the operator works on:

```java
price
quantity
100
3
```

### Expression

A combination of values, variables, operators, and other expressions that produces a value:

```java
price + quantity
```

---

## 4. Mental Model

Think of an operator as a **transformation or operation mechanism**.

```text
              OPERANDS
             /        \
            /          \
         value        value
            \          /
             \        /
              OPERATOR
                  │
                  ▼
               RESULT
```

For:

```java
int result = 10 + 20;
```

the structure is:

```text
10 ──┐
     ├── + ──→ 30
20 ──┘
```

The result can then be stored in a variable:

```java
int result = 10 + 20;
```

---

## 5. Java Syntax

### Arithmetic operators

```text
+    addition
-    subtraction
*    multiplication
/    division
%    remainder
```

Examples:

```java
int a = 10;
int b = 3;

int sum = a + b;
int difference = a - b;
int product = a * b;
int quotient = a / b;
int remainder = a % b;
```

---

## 6. Minimal Example

```java
package operators;

public class Operator01Addition {

    public static void main(String[] args) {

        int price = 100;
        int quantity = 3;

        int total = price + quantity;

        System.out.println(total);
    }
}
```

Compile:

```bash
javac operators/Operator01Addition.java
```

Run:

```bash
java operators.Operator01Addition
```

The result demonstrates that `+` operates on the values represented by `price` and `quantity`.

---

## 7. How It Works

Consider:

```java
int result = price + quantity;
```

Java evaluates the expression:

```text
price + quantity
```

If:

```text
price = 100
quantity = 3
```

then:

```text
100 + 3
  ↓
103
```

The resulting value is assigned to:

```java
result
```

Conceptually:

```text
price ──────→ 100
quantity ───→ 3

100 + 3
   ↓
  103
   ↓
result
```

This demonstrates an important relationship:

```text
VARIABLES
   ↓
provide values
   ↓
OPERATORS
   ↓
perform operations
   ↓
EXPRESSIONS
   ↓
produce values
```

---

## 8. Experiments

The experiments are intentionally small.

Each experiment should isolate one idea and be committed separately.

### Experiment 01 — Addition

```java
int price = 100;
int quantity = 3;

int result = price + quantity;

System.out.println(result);
```

Focus:

- operator
- operands
- expression
- result

---

### Experiment 02 — Subtraction

```java
int balance = 1000;
int withdrawal = 250;

int remaining = balance - withdrawal;

System.out.println(remaining);
```

Focus:

```text
1000 - 250 = 750
```

---

### Experiment 03 — Multiplication

```java
int price = 500;
int quantity = 3;

int total = price * quantity;

System.out.println(total);
```

Focus:

```text
500 × 3 = 1500
```

---

### Experiment 04 — Integer Division

```java
int total = 100;
int people = 3;

int share = total / people;

System.out.println(share);
```

Focus:

> What happens when integer values are divided and the result is not a whole number?

---

### Experiment 05 — Remainder

```java
int total = 100;
int people = 3;

int remainder = total % people;

System.out.println(remainder);
```

Focus:

> What does `%` actually return?

---

### Experiment 06 — Operator Precedence

```java
int result = 5 + 15 / 3 * 2 - 8 % 3;

System.out.println(result);
```

Focus:

- precedence
- division
- multiplication
- remainder
- addition
- subtraction

The goal is to learn **why Java evaluates the expression in a particular order**, not simply memorize the answer.

---

### Experiment 07 — Parentheses

Compare:

```java
int result1 = 10 + 20 * 3;
int result2 = (10 + 20) * 3;

System.out.println(result1);
System.out.println(result2);
```

Focus:

> How can parentheses deliberately change evaluation order?

---

### Experiment 08 — String Concatenation

```java
String firstName = "John";
String lastName = "Doe";

String fullName = firstName + " " + lastName;

System.out.println(fullName);
```

Focus:

> Why does `+` behave differently when Strings are involved?

---

### Experiment 09 — Increment and Decrement

```java
int count = 5;

count++;
System.out.println(count);

count--;
System.out.println(count);
```

Focus:

```text
count++
count--
```

These operators modify a variable's value.

---

### Experiment 10 — Break It: Division by Zero

```java
int number = 10;
int divisor = 0;

int result = number / divisor;

System.out.println(result);
```

Compile and execute it.

Focus:

> Why can compilation succeed while execution fails?

Expected runtime failure:

```text
java.lang.ArithmeticException: / by zero
```

This experiment connects operators with the Java execution model studied earlier.

---

## 9. Break It

Operators should be deliberately broken to understand their boundaries.

Examples include:

### Invalid syntax

```java
int result = 10 +;
```

### Type incompatibility

```java
int result = 10 + "hello";
```

### Division by zero

```java
int result = 10 / 0;
```

### Runtime division by zero

```java
int divisor = 0;
int result = 10 / divisor;
```

Each failure should be classified:

```text
SOURCE
  ↓
COMPILE-TIME ERROR
or
  ↓
RUNTIME ERROR
```

The goal is to understand **why** the failure occurs.

---

## 10. Debugging

When an operator-related problem occurs:

### OBSERVE

Read the complete compiler error or stack trace.

### REPRODUCE

Run the exact command again.

### ISOLATE

Identify the specific expression causing the problem.

Example:

```java
int result = number / divisor;
```

### HYPOTHESIS

Ask:

> What values are actually reaching this operator?

### TEST

Print or inspect the relevant variables.

### ROOT CAUSE

Determine whether the problem is:

- syntax
- type incompatibility
- precedence
- invalid operand
- runtime value
- incorrect business logic

### FIX

Make the smallest correct change.

### VERIFY

Compile and execute again.

### PREVENT REGRESSION

Add an experiment that protects the discovered behavior.

---

## 11. Common Mistakes

### Mistake 1 — Confusing `/` with `%`

```java
10 / 3
```

produces the integer quotient.

```java
10 % 3
```

produces the remainder.

---

### Mistake 2 — Ignoring operand types

```java
int result = 10 / 3;
```

does not produce the same kind of result as floating-point division.

---

### Mistake 3 — Assuming operators always produce the same behavior

For example:

```java
10 + 20
```

is arithmetic addition.

But:

```java
"Hello " + "World"
```

is String concatenation.

The operands matter.

---

### Mistake 4 — Ignoring precedence

```java
5 + 10 * 2
```

is not evaluated simply from left to right.

---

### Mistake 5 — Assuming compilation guarantees successful execution

This can compile:

```java
int divisor = 0;
int result = 10 / divisor;
```

but fail during execution.

---

## 12. Bad vs Good Design

### Poor reasoning

```java
int result = 5 + 15 / 3 * 2 - 8 % 3;
```

and relying entirely on memory to determine the evaluation order.

### Better reasoning

Break the expression into its precedence levels:

```text
division
multiplication
remainder
    ↓
addition/subtraction
```

For complicated business logic, parentheses can make intent explicit:

```java
int total = (price * quantity) + shipping;
```

The goal is not merely to make Java accept the expression.

The goal is to make the expression **correct, readable, and maintainable**.

---

## 13. Real-World Application

Consider an e-commerce order:

```java
double price = 1500.00;
int quantity = 3;

double subtotal = price * quantity;
```

The business workflow is:

```text
Product price
     ↓
Quantity
     ↓
multiplication
     ↓
Subtotal
```

Then additional business rules can be applied:

```text
Subtotal
   ↓
discount
   ↓
tax
   ↓
shipping
   ↓
final total
```

Operators therefore become part of the application's business logic.

### BUILD

Start with simple calculations such as:

```text
price × quantity
```

### VALIDATE

Test normal, zero, negative, and boundary values.

### LEARN

Observe how Java handles different operand types and runtime values.

### AVOID

Avoid complicated expressions that hide business rules. Prefer clear intermediate variables when they improve readability.

---

## 14. Key Takeaways

1. Operators perform operations on operands.
2. An operand can be a literal, variable, or expression.
3. An expression produces a value.
4. Arithmetic operators include `+`, `-`, `*`, `/`, and `%`.
5. `/` performs division.
6. `%` produces a remainder.
7. Operator precedence determines evaluation order.
8. Parentheses can explicitly control evaluation order.
9. Operand types affect operator behavior.
10. Some operator problems occur at compile time.
11. Other operator problems occur at runtime.
12. Operators transform the data supplied by variables.
13. Operators are fundamental to calculations and business logic.

---

## 15. Questions I Can Now Answer

After completing this module, I should be able to answer:

- What is an operator?
- What is an operand?
- What is an expression?
- What does `+` do?
- What does `-` do?
- What does `*` do?
- What does `/` do?
- What does `%` do?
- Why does integer division discard the fractional portion?
- What happens when division by zero occurs?
- What is operator precedence?
- Why are parentheses important?
- Why can `+` perform String concatenation?
- Why can an expression compile but fail at runtime?
- How do variable types affect operator behavior?
- How should complicated expressions be made readable?

---

## 16. Further Experiments

After the arithmetic operators are understood, investigate:

```text
1. Unary operators
2. Increment and decrement
3. Compound assignment
4. Relational operators
5. Equality operators
6. Logical operators
7. Boolean expressions
8. Short-circuit evaluation
9. Assignment expressions
10. Ternary operator
11. Operator precedence
12. Type promotion
13. Integer vs floating-point arithmetic
14. String concatenation
15. Operator behavior with different types
```

The next major category after arithmetic is **comparison and logical operators**, which will connect directly to conditions.

---

## 17. Reflection

Explain the following expression without memorizing a definition:

```java
int result = price * quantity;
```

Identify:

```text
price
*
quantity
price * quantity
result
```

Then explain the complete flow:

```text
VARIABLES
    ↓
values
    ↓
OPERANDS
    ↓
OPERATOR
    ↓
EXPRESSION
    ↓
RESULT
    ↓
VARIABLE
```

The goal is to understand how Java **works with data**, not simply memorize operator symbols.

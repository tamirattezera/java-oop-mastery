# Java Variables

## 1. Overview

A variable is a named location used by a Java program to work with a value.

Variables allow programs to give meaningful names to data and use that data throughout the execution of a program.

For example:

```java
int age = 22;
```

This creates an `int` variable named `age` and initializes it with the value `22`.

Variables are one of the foundations of Java programming because operators, conditions, loops, methods, arrays, objects, and larger application logic all work with data.

---

## 2. Why Variables Exist

Without variables, programs would repeatedly work directly with unnamed values:

```java
System.out.println(22);
System.out.println(22 + 5);
System.out.println(22 * 2);
```

With variables:

```java
int age = 22;

System.out.println(age);
System.out.println(age + 5);
System.out.println(age * 2);
```

The variable gives the value a meaningful identity.

The engineering problem variables solve is:

> **How can a program name, store, reuse, and manipulate data during execution?**

---

## 3. Core Idea

A basic variable declaration follows this structure:

```java
type variableName;
```

Example:

```java
int age;
```

A declaration tells Java:

- the variable's type is `int`
- the variable's name is `age`

A variable can then receive a value:

```java
age = 22;
```

Or declaration and initialization can happen together:

```java
int age = 22;
```

---

## 4. Mental Model

Think of a variable as a **named piece of program state**.

```text
             variable
                │
        ┌───────┴───────┐
        │               │
      name             value
        │               │
       age              22
```

The name gives the program a way to refer to the value.

```java
int age = 22;

System.out.println(age);
```

Conceptually:

```text
age ─────────► 22
```

If the value is changed:

```java
age = 25;
```

the variable now represents the new value:

```text
age ─────────► 25
```

---

## 5. Java Syntax

### Declaration

```java
int age;
```

The variable is declared but has not yet been initialized.

### Assignment

```java
age = 22;
```

A value is assigned to the variable.

### Initialization

Initialization gives a variable its first value.

```java
int age = 22;
```

This combines declaration and initialization.

### Declaration + assignment separately

```java
int age;
age = 22;
```

Both forms are valid when the variable is definitely assigned before it is used.

---

## 6. Minimal Example

```java
package variables;

public class VariableBasics {

    public static void main(String[] args) {

        int age = 22;

        System.out.println(age);
    }
}
```

Compile:

```bash
javac variables/VariableBasics.java
```

Run:

```bash
java variables.VariableBasics
```

Output:

```text
22
```

---

## 7. How It Works

The statement:

```java
int age = 22;
```

can be understood as:

```text
int
 │
 └── type

age
 │
 └── variable name

22
 │
 └── initial value
```

The compiler checks the Java source code and produces bytecode.

```text
VariableBasics.java
        │
        │ javac
        ▼
VariableBasics.class
        │
        │ java
        ▼
       JVM
        │
        ▼
    execution
```

The variable participates in the program's execution when the compiled program runs.

---

## 8. Experiments

### Experiment 1 — Declaration + Initialization

```java
int age = 22;

System.out.println(age);
```

Expected output:

```text
22
```

Compile:

```bash
javac variables/VariableBasics.java
```

Run:

```bash
java variables.VariableBasics
```

---

### Experiment 2 — Declaration Then Assignment

```java
int age;

age = 22;

System.out.println(age);
```

This is also valid because `age` receives a value before it is used.

Execution flow:

```text
declare
   ↓
assign
   ↓
use
```

---

### Experiment 3 — Use Before Initialization

```java
int age;

System.out.println(age);
```

Compilation fails with an error similar to:

```text
variable age might not have been initialized
```

This is a **compile-time error**.

Java does not allow a local variable to be read when the compiler cannot prove that it has been assigned a value.

---

### Experiment 4 — Definite Assignment

```java
int age;

if (true) {
    age = 22;
}

System.out.println(age);
```

This compiles because Java's compiler can determine that the assignment occurs before `age` is used.

This introduces the concept of **definite assignment**.

---

## 9. Break It

Remove the assignment:

```java
int age;

System.out.println(age);
```

Compile:

```bash
javac variables/VariableBasics.java
```

Observe the compiler error.

The important lesson is:

```text
declaration ≠ initialization
```

Declaring a local variable does not automatically make it ready to use.

---

## 10. Debugging

When Java reports:

```text
variable age might not have been initialized
```

use this debugging process:

### OBSERVE

Read the compiler error carefully.

### REPRODUCE

Compile the program again:

```bash
javac variables/VariableBasics.java
```

### ISOLATE

Find the variable mentioned by the compiler:

```java
int age;
```

### HYPOTHESIS

Ask:

> Where is `age` definitely assigned before it is used?

### TEST

Add an assignment:

```java
age = 22;
```

### ROOT CAUSE

The local variable was used without a value that the compiler could prove was assigned.

### FIX

```java
int age = 22;
```

### VERIFY

Compile and run again.

---

## 11. Common Mistakes

### Mistake 1 — Thinking declaration gives a usable value

```java
int age;

System.out.println(age);
```

This does not work for a local variable.

---

### Mistake 2 — Confusing declaration with initialization

```java
int age;
```

is declaration.

```java
int age = 22;
```

is declaration + initialization.

---

### Mistake 3 — Assuming the JVM will automatically provide a value

For local variables, Java requires definite assignment before use.

The compiler catches this before execution begins.

---

### Mistake 4 — Confusing compilation with execution

```bash
javac variables/VariableBasics.java
```

compiles the source.

```bash
java variables.VariableBasics
```

runs the compiled class.

A compilation error means execution does not begin from that compilation attempt.

---

## 12. Bad vs Good Design

### Bad

```java
int age;

System.out.println(age);
```

The variable is used before it has been definitely assigned.

### Good

```java
int age = 22;

System.out.println(age);
```

The variable is initialized before use.

### Also Good

```java
int age;

age = 22;

System.out.println(age);
```

The variable is assigned before use.

The important principle is:

> **Establish valid state before using it.**

---

## 13. Real-World Application

Variables represent changing pieces of application state.

For example, an e-commerce system may need:

```java
int quantity = 3;
double price = 1250.00;
boolean paid = false;
```

Conceptually:

```text
Order
 ├── quantity → 3
 ├── price    → 1250.00
 └── paid     → false
```

The application can then operate on this state.

For example:

```java
double total = price * quantity;
```

This is where variables begin connecting to the next topic:

```text
Variables
    ↓
hold data
    ↓
Operators
    ↓
process data
```

---

## 14. Key Takeaways

1. A variable gives a name to data used by a program.
2. Declaration introduces a variable.
3. Assignment gives a variable a value.
4. Initialization gives a variable its first value.
5. `int age = 22;` combines declaration and initialization.
6. Local variables must be definitely assigned before they are used.
7. The compiler checks definite assignment before execution.
8. Compilation and execution are different stages.
9. Variables represent program state.
10. Variables are the foundation for operators, control flow, methods, arrays, and object-oriented programming.

---

## 15. Questions I Can Now Answer

After completing this experiment, I should be able to answer:

- What is a variable?
- Why do variables exist?
- What is declaration?
- What is assignment?
- What is initialization?
- What is the difference between `int age;` and `int age = 22;`?
- Why can't a local variable be used before initialization?
- What is definite assignment?
- Is an uninitialized local variable a compile-time or runtime problem?
- What is the difference between `javac` and `java`?
- Why does compilation need to succeed before execution can begin?

---

## 16. Further Experiments

Future experiments should investigate:

```text
1. Changing a variable's value
2. Multiple variables
3. Different primitive types
4. Variable naming
5. Type compatibility
6. Invalid assignments
7. Integer vs floating-point values
8. Boolean variables
9. Character variables
10. Scope
11. Local variables
12. Parameters
13. Variables inside methods
14. Variables and expressions
15. Variables interacting with operators
```

---

## 17. Reflection

Before moving to operators, explain this without looking at the code:

> **What happens from the moment `int age = 22;` is written in the source file until `22` is printed on the terminal?**

Also explain the difference between:

```java
int age;
```

```java
age = 22;
```

and:

```java
int age = 22;
```

The goal is not memorization.

The goal is to understand how Java establishes and uses program state.

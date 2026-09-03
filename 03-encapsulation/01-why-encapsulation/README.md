# 01 — Why Encapsulation Exists

## Overview

Encapsulation is often introduced as:

> "Making variables private and accessing them through methods."

While this is related to encapsulation, it does not explain **why encapsulation exists**.

This experiment starts with the engineering problem that encapsulation attempts to solve:

> **What happens when an object's internal state can be modified directly by any external code?**

The goal is to understand encapsulation as a mechanism for managing **state, responsibility, complexity, and change** in software systems.

---

# Learning Objectives

After completing this experiment, the learner should be able to:

* Explain why uncontrolled access to object state is dangerous.
* Distinguish type-valid data from business-valid data.
* Understand the concept of state ownership.
* Identify the risks of shared and directly mutable state.
* Explain why objects should control important state changes.
* Recognize how encapsulation improves maintainability and debugging.

---

# Experiment 01 — Unprotected State

## Objective

This experiment demonstrates what happens when an object's internal state is publicly accessible.

The `BankAccount` class exposes its balance directly:

```java
class BankAccount {

    public double balance;
}
```

External code can therefore modify the balance without asking the object to validate the change.

---

## Code

```java
class BankAccount {

    public double balance;
}

public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        account.balance = 1000;

        System.out.println("Initial balance: " + account.balance);

        account.balance = -5000;

        System.out.println("Updated balance: " + account.balance);
    }
}
```

---

## How to Run

Compile the program:

```bash
javac Main.java
```

Run the program:

```bash
java Main
```

---

## Expected Output

```text
Initial balance: 1000.0
Updated balance: -5000.0
```

---

# What Happened?

Java successfully executes the following instruction:

```java
account.balance = -5000;
```

This happens because `balance` is declared as `public`.

The Java compiler and JVM understand that `-5000` is a valid `double` value.

However, Java does not automatically know the business rules of a particular application.

This creates an important distinction:

```text
Type-valid value
       ≠
Business-valid value
```

For example:

```text
-5000
```

is technically a valid numeric value.

However, depending on the rules of the application, it may represent an invalid bank account balance.

---

# The Engineering Problem

The main problem is not simply the use of the `public` keyword.

The deeper problem is:

> **The object does not control its own important state.**

When internal state is publicly accessible:

```text
External Code
      │
      ▼
┌──────────────────┐
│   BankAccount    │
│                  │
│ public balance   │◄── Direct modification
│                  │
└──────────────────┘
```

Any part of the application can potentially execute:

```java
account.balance = -5000;
```

The object cannot validate the change before it happens.

---

# State Ownership

A stronger software design asks:

> Who is responsible for keeping this state valid?

## Weak Design

```text
Many external components
        │
        ▼
Directly modify state
        │
        ▼
Object state may become invalid
```

Responsibility is distributed across the application.

Every developer must remember the rules.

This creates a fragile system.

---

## Stronger Design

```text
External Code
      │
      │ Request an operation
      ▼
┌─────────────────────────┐
│      BankAccount        │
│                         │
│   Validates request     │
│          │              │
│          ▼              │
│   Changes its state     │
│                         │
└─────────────────────────┘
```

The object becomes responsible for protecting its own state and business rules.

This is the engineering motivation behind encapsulation.

---

# Experiment 02 — Uncontrolled Modification

The problem becomes more visible when multiple methods can modify the same object.

```java
class BankAccount {

    public double balance;
}

public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        account.balance = 1000;

        System.out.println("Initial balance: " + account.balance);

        withdraw(account);

        System.out.println("After withdrawal: " + account.balance);

        randomSystemUpdate(account);

        System.out.println("Final balance: " + account.balance);
    }

    public static void withdraw(BankAccount account) {
        account.balance = account.balance - 200;
    }

    public static void randomSystemUpdate(BankAccount account) {
        account.balance = -999999;
    }
}
```

---

## Prediction

Before running the program, answer:

1. What will the final balance be?
2. Which method corrupted the account state?
3. How difficult would this problem be to locate in a system with hundreds of classes?
4. How would the difficulty increase if multiple developers could directly modify the same state?

---

## Expected Output

```text
Initial balance: 1000.0
After withdrawal: 800.0
Final balance: -999999.0
```

---

# Why This Becomes Dangerous in Large Software

Imagine the same object being used by:

```text
Payment Service
      │
Order Service
      │
Admin Panel
      │
Reporting System
      │
Background Job
      │
External API
      │
      ▼
 BankAccount.balance
```

If the state is directly accessible, determining where an invalid value originated becomes increasingly difficult.

This creates several engineering problems.

## 1. Coupling

External code becomes dependent on the internal representation of the object.

If the internal design changes, many parts of the application may also need to change.

---

## 2. Debugging Difficulty

When many components can modify the same state, finding the source of an incorrect value requires tracing potentially many execution paths.

The question becomes:

> Which component changed the state, when, and why?

---

## 3. Unclear Responsibility

If everyone can modify an object's state:

```text
Everyone can change it
        ↓
No single component controls it
```

A well-designed object should clearly define which component is responsible for protecting important rules.

---

## 4. Maintenance Risk

As the application grows, developers must remember invisible rules.

For example:

```text
Do not set the balance directly.
Always validate the amount.
Never allow invalid transactions.
```

A stronger design should encode important rules into the software rather than depending entirely on developers remembering them.

---

# Break It

Try assigning unusual values:

```java
account.balance = -1;

account.balance = Double.NaN;

account.balance = Double.POSITIVE_INFINITY;
```

Then ask:

> Should a financial object accept every value that the Java `double` type allows?

Again:

```text
Java allows a value
        ≠
The application should accept the value
```

---

# Real-World Connection

The same problem appears in backend systems.

Consider an order with the following lifecycle:

```text
PENDING
   ↓
PAID
   ↓
PROCESSING
   ↓
SHIPPED
```

An API request should not necessarily be allowed to directly perform:

```text
order.status = "SHIPPED";
```

The application may need to verify:

* Has the customer paid?
* Is the order valid?
* Is the current state allowed to transition?
* Does the user have permission?

A safer architecture looks like:

```text
External Request
      ↓
Validation
      ↓
Business Rules
      ↓
Controlled State Transition
```

The same fundamental principle applies:

> Important state should not change without controlled rules.

---

# Key Mental Model

```text
UNPROTECTED OBJECT

External Code
      │
      ▼
┌──────────────────────┐
│      Object          │
│                      │
│   Public State       │
│                      │
└──────────────────────┘

Anyone can directly modify the state.
```

```text
CONTROLLED OBJECT

External Code
      │
      │ Request behavior
      ▼
┌──────────────────────┐
│      Object          │
│                      │
│   Controlled Rules   │
│          ↓           │
│   Internal State     │
│                      │
└──────────────────────┘

The object controls important state changes.
```

---

# Key Lessons

## 1. Public state creates uncontrolled access

When important state is directly accessible, external code can bypass business rules.

## 2. Type-valid does not mean business-valid

A value can be valid according to the Java type system while being invalid according to application requirements.

## 3. Objects should own important state

The component responsible for protecting state should also control how that state changes.

## 4. Encapsulation is an engineering solution

Encapsulation is not simply:

```java
private double balance;
```

Its deeper purpose is to:

* Protect invariants.
* Control state transitions.
* Reduce coupling.
* Improve maintainability.
* Make debugging easier.
* Establish clear responsibility boundaries.

---

# Common Misconception

## ❌ Encapsulation means making everything private

Not exactly.

The deeper question is:

> What should be exposed, and what should be protected?

Encapsulation involves designing a controlled boundary around an object's responsibilities and state.

---

# Next Step

The next experiment explores:

```text
02-private-state
```

We will introduce:

```java
private
```

But we will investigate an important question:

> **What does `private` actually protect, and what does it not protect?**

The goal is not to memorize access modifiers.

The goal is to understand how Java uses access control as one mechanism for implementing encapsulation.

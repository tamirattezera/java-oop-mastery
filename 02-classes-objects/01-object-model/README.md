# 01 — Object Model

> **Understand objects from first principles — not syntax memorization.**

This lab establishes the foundation for Java's object-oriented model.

The goal is to understand what a **class**, **object**, **identity**, **state**, and **behavior** mean at runtime, and why object-oriented systems organize data and behavior around objects.

---

## 1. Learning Objective

By the end of this lab, I should be able to explain:

- What a class is
- What an object is
- The difference between a class and an object
- Why one class can produce many objects
- What object identity means
- What object state means
- What object behavior means
- What `new` conceptually does
- What a reference variable represents
- Why two variables can refer to the same object
- Why objects are useful for modeling real systems

The target is **mental-model mastery**, not syntax memorization.

---

## 2. Core Mental Model

An object can be understood through three fundamental properties:

```text
                    OBJECT
                      │
          ┌───────────┼───────────┐
          ↓           ↓           ↓
       IDENTITY      STATE      BEHAVIOR
          │           │           │
      which one?   what is it?  what can it do?
```

### Identity

Identity answers:

> Which particular object is this?

Two objects may contain identical data while still being different objects.

### State

State represents the object's current data.

Example:

```text
Patient
├── name = "Abebe"
└── age  = 25
```

### Behavior

Behavior represents operations the object can perform.

Example:

```text
Patient
├── updateAge()
├── bookAppointment()
└── introduce()
```

The fundamental relationship is:

```text
OBJECT
├── owns state
└── provides behavior
```

---

## 3. Class vs Object

A **class** defines a type and describes the structure and behavior that its instances can have.

An **object** is a concrete runtime instance of that class.

```text
             Patient class
                   │
          ┌────────┴────────┐
          ↓                 ↓
       Patient            Patient
       object              object
          p1                  p2
```

For example:

```java
class Patient {
    String name;
    int age;

    void introduce() {
        System.out.println("My name is " + name);
    }
}
```

The class describes:

```text
Patient
├── name
├── age
└── introduce()
```

Objects are then created from that type:

```java
Patient p1 = new Patient();
Patient p2 = new Patient();
```

Now there are:

```text
1 class
2 objects
```

The objects share the same class definition but can contain different state.

---

## 4. One Class → Many Objects

```java
Patient p1 = new Patient();
Patient p2 = new Patient();

p1.name = "Abebe";
p2.name = "Marta";
```

Conceptually:

```text
             Patient class
                   │
          ┌────────┴────────┐
          ↓                 ↓
        p1 object         p2 object
        name=Abebe        name=Marta
```

This is one of the major benefits of classes.

We define the structure and behavior once, then create many independent instances.

---

## 5. What `new` Means

Consider:

```java
Patient p1 = new Patient();
```

Do not think:

> "`p1` is the object."

A better model is:

```text
Patient p1 = new Patient();
│       │       │
│       │       └── create a new instance
│       │
│       └────────── reference variable
│
└────────────────── declared type
```

Conceptually:

```text
                    new Patient()
                         │
                         ↓
                  Patient object
                         │
                         ↓
              reference returned
                         │
                         ↓
                        p1
```

So:

```text
p1 ─────────→ Patient object
```

The variable `p1` is a **reference variable** that can refer to a `Patient` object.

References and identity are studied in depth in:

```text
05-references-and-identity
```

---

## 6. Runtime Perspective

Objects are runtime entities.

The Java execution pipeline can be viewed as:

```text
Main.java
    ↓
javac
    ↓
.class bytecode
    ↓
JVM
    ↓
program execution
    ↓
new Patient()
    ↓
Patient object exists
```

This distinction matters:

> A class defines the type/model; an object is an instance that exists during program execution.

The source code describes what can exist.

Runtime execution creates actual instances.

---

## 7. Experiment

### Experiment Question

> What is the relationship between a class and the objects created from it?

### Code

```java
class Patient {
    String name;
    int age;

    void introduce() {
        System.out.println("My name is " + name);
    }
}

public class Main {
    public static void main(String[] args) {

        Patient p1 = new Patient();
        Patient p2 = new Patient();

        p1.name = "Abebe";
        p1.age = 25;

        p2.name = "Marta";
        p2.age = 31;

        p1.introduce();
        p2.introduce();
    }
}
```

Compile and run:

```bash
javac Main.java
java Main
```

Expected output:

```text
My name is Abebe
My name is Marta
```

---

## 8. Prediction Before Execution

Before running the program, answer:

### Q1

How many `Patient` classes exist?

### Q2

How many `Patient` objects are created?

### Q3

Does `p1` have the same state as `p2`?

### Q4

What happens if:

```java
p1.name = "Kebede";
```

What is:

```java
p2.name
```

### Q5

What does `p1` represent?

Only run the program after making your prediction.

---

## 9. Break-It Experiment

Change:

```java
Patient p2 = new Patient();
```

to:

```java
Patient p2 = p1;
```

Now the model changes.

Instead of:

```text
p1 → Object A

p2 → Object B
```

we have:

```text
p1 ─────┐
        ↓
     Object A
        ↑
p2 ─────┘
```

Both variables refer to the same object.

Test:

```java
p1.name = "Abebe";
p2.name = "Marta";

System.out.println(p1.name);
System.out.println(p2.name);
```

Expected:

```text
Marta
Marta
```

This experiment demonstrates why **reference identity** matters.

Do not simply memorize the result.

Explain why the result occurs.

---

## 10. Object Responsibility

Objects are useful because they create meaningful boundaries around system concepts.

For example:

```text
Hospital System
│
├── Patient
├── Doctor
├── Appointment
├── MedicalRecord
└── Payment
```

A `Patient` may own:

```text
State
├── patientId
├── name
├── age
└── phone
```

and provide behavior such as:

```text
Behavior
├── updatePhone()
├── updateAge()
└── introduce()
```

This leads to an important design question:

> Which object should own this state and behavior?

That question becomes increasingly important as systems grow.

---

## 11. State and Behavior Belong Together

A useful object-oriented model is:

```text
Patient
│
├── State
│   ├── name
│   ├── age
│   └── phone
│
└── Behavior
    ├── updatePhone()
    ├── updateAge()
    └── introduce()
```

Behavior often operates on the object's own state.

For example:

```java
void introduce() {
    System.out.println("My name is " + name);
}
```

The method uses the state of the particular object receiving the call.

Therefore:

```java
p1.introduce();
```

and:

```java
p2.introduce();
```

can produce different results even though both calls execute the same method definition.

---

## 12. Engineering Insight

Object-oriented programming is not fundamentally about writing classes.

The deeper question is:

> **How should a system be divided into meaningful objects with clear responsibilities?**

Instead of thinking only:

```text
What classes do I need?
```

start asking:

```text
What objects exist?

What state does each object own?

What behavior belongs to each object?

Which object is responsible for changing that state?

Which objects need to collaborate?
```

These questions form the foundation for later OOP design.

---

## 13. Connection to Encapsulation

This lab establishes:

```text
OBJECT
├── owns state
└── provides behavior
```

But we currently allow direct access:

```java
patient.age = -500;
```

That creates a design problem.

A real object may need to protect its state:

```text
             OBJECT
                │
        ┌───────┴───────┐
        ↓               ↓
      STATE           BEHAVIOR
        │               │
        │          controls access
        │               │
        └───────────────┘
                ↓
          ENCAPSULATION
```

This is the bridge into:

```text
03 — Encapsulation
```

The key question becomes:

> **Who should be allowed to change an object's state, and under what rules?**

---

## 14. Common Misconceptions

### Misconception 1

> A class is an object.

Incorrect.

```text
class → defines a type
object → runtime instance
```

---

### Misconception 2

> `p1` is the object itself.

Better:

```text
p1 → reference to an object
```

---

### Misconception 3

> Every object created from a class is identical.

Incorrect.

They share the same type and class-defined behavior, but their instance state can differ.

---

### Misconception 4

> `new` creates a variable.

Incorrect.

The variable is declared separately:

```java
Patient p1;
```

`new` is involved in creating an object:

```java
new Patient();
```

The reference is then assigned:

```java
p1 = new Patient();
```

---

### Misconception 5

> Objects are created when the Java source code is written.

Incorrect.

Actual object instances are created during runtime when execution reaches object-creation operations.

---

## 15. Engineering Vocabulary

| Term      | Meaning                                                             |
| --------- | ------------------------------------------------------------------- |
| Class     | A type definition describing possible object structure and behavior |
| Object    | A concrete runtime instance                                         |
| Instance  | Another way of describing an object created from a class            |
| State     | The current data held by an object                                  |
| Behavior  | Operations an object can perform                                    |
| Identity  | The particular identity of an object                                |
| Reference | A value that can refer to an object                                 |
| `new`     | Expression used to create a new object instance                     |

---

## 16. Mastery Questions

I should be able to answer these without looking at the code:

1. What is a class?
2. What is an object?
3. Why can one class create many objects?
4. What is object identity?
5. What is object state?
6. What is object behavior?
7. What does `new` conceptually do?
8. What does a reference variable represent?
9. Why can two variables refer to the same object?
10. Why can two objects of the same class have different state?
11. Why is object-oriented programming useful for modeling real systems?
12. Why does object modeling naturally lead toward encapsulation?

---

## 17. Mastery Test

Without looking at the previous code, explain this:

```java
Patient p1 = new Patient();
Patient p2 = new Patient();
```

Your explanation should cover:

```text
Patient
  ↓
declared type

p1 / p2
  ↓
reference variables

new Patient()
  ↓
object creation

two executions of new
  ↓
two distinct objects
```

Then explain:

```java
Patient p2 = p1;
```

using a diagram.

A strong answer should show:

```text
p1 ─────┐
        ↓
      Object
        ↑
p2 ─────┘
```

---

## 18. Learning Record

For this experiment, document:

### Question

What is the difference between a class and an object?

### Hypothesis

What did I predict before running the program?

### Experiment

What code did I execute?

### Observation

What actually happened?

### Explanation

Why did it happen?

### Break-It Result

What happened when I made two references point to the same object?

### Engineering Insight

What does this teach me about object-oriented design?

### Remaining Questions

What do I still not understand?

---

## 19. Definition to Internalize

> **A class defines a type and the structure and behavior its instances can have; an object is a concrete runtime instance with its own identity and instance state.**

But don't stop at the definition.

The real mental model is:

```text
                  CLASS
                    │
              defines a type
                    │
                  new
                    ↓
                 OBJECT
                    │
        ┌───────────┼───────────┐
        ↓           ↓           ↓
     IDENTITY      STATE      BEHAVIOR
        │           │           │
     which one?  current      what it
                  data        can do
```

---

## 20. Mastery Gate

This lab is complete when I can:

- Explain class vs object from first principles
- Draw the relationship between a class and its instances
- Explain object identity
- Explain object state
- Explain object behavior
- Trace `new` conceptually
- Explain references at a basic level
- Predict object behavior before execution
- Explain the shared-reference experiment
- Connect object ownership to responsibility
- Explain why this foundation is necessary for encapsulation

### Final principle

> **Don't ask only "How do I create an object?" Ask "What object should exist, what state should it own, what behavior should it provide, and what responsibility should it have?"**

---

## Next

```text
01 Object Model
      ↓
02 State & Behavior
      ↓
03 Constructors
      ↓
04 Instance vs Class
      ↓
05 References & Identity
```

The next lab investigates **how an object gets its initial state** and why construction is a distinct phase of an object's lifecycle.

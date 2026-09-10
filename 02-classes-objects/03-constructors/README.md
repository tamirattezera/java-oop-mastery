# 03 — Constructors

> **Understand how objects are initialized and why construction is a critical design boundary.**

This lab investigates Java constructors from first principles.

The goal is not to memorize constructor syntax. The goal is to understand:

```text
object creation
      ↓
initialization
      ↓
constructor execution
      ↓
valid initial state
```

A constructor is one of the first places where object-oriented programming moves from **syntax** toward **object design**.

---

## 1. Learning Objective

By the end of this lab, I should be able to explain:

- What a constructor is
- Why constructors exist
- How constructors differ from methods
- What happens conceptually when `new` is executed
- Why constructors have the same name as their class
- Why constructors have no return type
- How constructor parameters initialize object state
- Why `this` is commonly used inside constructors
- What a default constructor is
- When Java provides a default constructor
- What happens when a class declares its own constructor
- What constructor overloading means
- Why constructors can enforce valid initial state
- Why object construction is a design boundary
- How constructors connect to encapsulation and invariants

---

# 2. Core Mental Model

The most important model is:

```text
              OBJECT DOES NOT EXIST
                       │
                       │ new
                       ↓
                object creation
                       │
                       ↓
                 initialization
                       │
                       ↓
                constructor runs
                       │
                       ↓
               initialized object
                       │
                       ↓
                  reference
```

For example:

```java
Patient p = new Patient("Abebe", 25);
```

Conceptually:

```text
new Patient("Abebe", 25)
          │
          ↓
    create instance
          │
          ↓
  initialize object
          │
          ↓
 execute constructor
          │
          ↓
 initialized Patient
          │
          ↓
          p
```

The constructor's responsibility is to establish the object's **initial state**.

---

# 3. What Is a Constructor?

A constructor is a special class member invoked during object creation to initialize a newly created object.

Example:

```java
class Patient {

    String name;
    int age;

    Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Creating an object:

```java
Patient patient = new Patient("Abebe", 25);
```

After construction:

```text
patient
├── name = "Abebe"
└── age  = 25
```

The constructor connected the supplied values to the object's state.

---

# 4. Constructor vs Method

A constructor and a method are not the same thing.

### Constructor

```java
Patient(String name, int age) {
    this.name = name;
    this.age = age;
}
```

Purpose:

```text
initialize a new object
```

### Method

```java
void introduce() {
    System.out.println(name);
}
```

Purpose:

```text
perform behavior on an existing object
```

Mental model:

```text
CONSTRUCTOR
    ↓
object comes into existence
    ↓
initial state established
    ↓
METHODS
    ↓
object performs behavior
```

---

# 5. Constructor Rules

A constructor has several defining characteristics.

## Same Name as Class

For:

```java
class Patient {
```

the constructor is:

```java
Patient() {
}
```

Not:

```java
Person() {
}
```

---

## No Return Type

Correct:

```java
Patient() {
}
```

Incorrect:

```java
void Patient() {
}
```

The second example is a method named `Patient`, not a constructor.

---

## Called During Construction

Constructors are normally invoked through:

```java
new Patient();
```

or:

```java
new Patient("Abebe", 25);
```

---

# 6. First Experiment

Create:

```text
03-constructors/
├── Main.java
└── README.md
```

Use:

```java
class Patient {

    String name;
    int age;

    Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void introduce() {
        System.out.println(
            "My name is " + name + ", age " + age
        );
    }
}

public class Main {

    public static void main(String[] args) {

        Patient p1 = new Patient("Abebe", 25);
        Patient p2 = new Patient("Marta", 31);

        p1.introduce();
        p2.introduce();
    }
}
```

Compile:

```bash
javac Main.java
```

Run:

```bash
java Main
```

Expected:

```text
My name is Abebe, age 25
My name is Marta, age 31
```

---

# 7. Prediction Before Execution

Before running the program, answer:

### Q1

How many `Patient` objects are created?

### Q2

How many times does the constructor execute?

### Q3

What is the initial state of `p1` after construction?

### Q4

What is the initial state of `p2`?

### Q5

Which constructor argument becomes `p1.name`?

### Q6

Why are the states of `p1` and `p2` different?

Run the program only after making your predictions.

---

# 8. Understanding `this`

Consider:

```java
Patient(String name, int age) {
    this.name = name;
    this.age = age;
}
```

There are two different things called `name`:

```text
this.name
    │
    └── object's field

name
    │
    └── constructor parameter
```

Therefore:

```java
this.name = name;
```

means:

> Set the current object's `name` field to the value received by the constructor parameter.

Conceptually:

```text
argument
"Abebe"
   │
   ↓
parameter: name
   │
   ↓
this.name
   │
   ↓
object state
```

The keyword `this` refers conceptually to the **current object**.

---

# 9. Break-It Experiment: Remove `this`

Change:

```java
Patient(String name, int age) {
    this.name = name;
    this.age = age;
}
```

to:

```java
Patient(String name, int age) {
    name = name;
    age = age;
}
```

Run the program.

The object's fields remain at their default values:

```text
name = null
age  = 0
```

Why?

Because:

```java
name = name;
```

does not mean:

> Set the object's field.

It effectively means:

> Assign the parameter back to itself.

Mental model:

```text
name = name
 ↑     ↑
 │     │
parameter
```

There is no explicit reference to the object's field.

The correct version is:

```java
this.name = name;
```

---

# 10. Constructor as Initialization Boundary

Consider:

```java
class BankAccount {

    double balance;

    BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
}
```

Then:

```java
BankAccount account = new BankAccount(1000);
```

The object starts with:

```text
BankAccount
└── balance = 1000
```

This is valuable because the constructor answers:

> **What state should an object have immediately after creation?**

That is a design question, not merely a syntax question.

---

# 11. Valid Initial State

A constructor can also enforce rules.

Suppose:

```text
BankAccount
└── balance must not be negative
```

We can express that:

```java
class BankAccount {

    private double balance;

    BankAccount(double initialBalance) {

        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                "Initial balance cannot be negative"
            );
        }

        this.balance = initialBalance;
    }
}
```

Now:

```java
BankAccount account = new BankAccount(1000);
```

is valid.

But:

```java
BankAccount account = new BankAccount(-500);
```

is rejected.

The conceptual flow becomes:

```text
requested object
      │
      ↓
 constructor
      │
      ↓
 validate initial state
    ↙       ↘
 valid     invalid
   ↓          ↓
 object     reject
```

This is the beginning of **invariant protection**.

---

# 12. Constructor and Invariants

An invariant is a condition that should remain true for an object.

Example:

```text
BankAccount
    balance >= 0
```

If an object is constructed with:

```text
balance = -500
```

the object starts in an invalid state.

A constructor can prevent that.

Therefore:

> **Construction is an opportunity to establish the conditions that must be true for an object to be valid.**

This becomes especially important when we reach:

```text
03 — Encapsulation
05 — Invariants
```

---

# 13. The Default Constructor

Consider:

```java
class Patient {

    String name;
}
```

We can write:

```java
Patient p = new Patient();
```

even though no constructor appears in the source code.

Why?

If a class declares **no constructor**, Java provides a default no-argument constructor.

Conceptually:

```text
class Patient
     │
     │ no constructor declared
     ↓
compiler provides default constructor
     │
     ↓
new Patient()
```

---

# 14. Important Rule

Now declare a constructor:

```java
class Patient {

    String name;

    Patient(String name) {
        this.name = name;
    }
}
```

Then:

```java
Patient p = new Patient();
```

does not compile.

Why?

Because the class now has:

```text
Patient(String)
```

but does not have:

```text
Patient()
```

Java does not automatically add the no-argument constructor once you have declared a constructor yourself.

If you need both, declare both explicitly:

```java
class Patient {

    String name;

    Patient() {
        this.name = "Unknown";
    }

    Patient(String name) {
        this.name = name;
    }
}
```

---

# 15. Break-It Experiment: Default Constructor

Try:

```java
class Patient {

    String name;

    Patient(String name) {
        this.name = name;
    }
}

public class Main {

    public static void main(String[] args) {

        Patient p = new Patient();
    }
}
```

Compile:

```bash
javac Main.java
```

Observe the compiler error.

Then fix it by either:

### Option A

Provide an argument:

```java
Patient p = new Patient("Abebe");
```

### Option B

Add a no-argument constructor:

```java
Patient() {
    this.name = "Unknown";
}
```

The goal is not merely to fix the error.

Explain **why** the error occurred.

---

# 16. Constructor Overloading

A class can provide multiple constructors with different parameter lists.

Example:

```java
class Patient {

    String name;
    int age;

    Patient() {
        this.name = "Unknown";
        this.age = 0;
    }

    Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Now both are valid:

```java
Patient p1 = new Patient();

Patient p2 = new Patient("Abebe", 25);
```

The constructors represent different initialization paths.

```text
             Patient
                │
       ┌────────┴────────┐
       ↓                 ↓
   Patient()       Patient(String,int)
       │                 │
       ↓                 ↓
 default state      supplied state
```

This is called **constructor overloading**.

---

# 17. Overloading Is About the Parameter List

These are different constructors:

```java
Patient()
Patient(String)
Patient(String, int)
```

because their parameter lists differ.

But this is not allowed:

```java
Patient(String name)
Patient(String age)
```

Parameter names alone do not distinguish overloaded constructors.

The parameter types and number/order matter.

---

# 18. Constructor Chaining with `this(...)`

When multiple constructors exist, duplicated initialization can become a problem.

Instead of:

```java
class Patient {

    String name;
    int age;

    Patient() {
        this.name = "Unknown";
        this.age = 0;
    }

    Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

we can delegate:

```java
class Patient {

    String name;
    int age;

    Patient() {
        this("Unknown", 0);
    }

    Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Here:

```java
this("Unknown", 0);
```

means:

> Invoke another constructor of the same class.

Mental model:

```text
new Patient()
      ↓
Patient()
      ↓
this("Unknown", 0)
      ↓
Patient(String, int)
      ↓
state initialized
```

This is called **constructor chaining**.

---

# 19. Important `this` Distinction

You have now seen two different uses of `this`.

### Current object

```java
this.name
```

means:

```text
the current object's name field
```

### Another constructor

```java
this("Unknown", 0);
```

means:

```text
invoke another constructor in this class
```

Same keyword.

Different context.

---

# 20. Constructor Execution vs Method Execution

Compare:

```java
Patient p = new Patient("Abebe", 25);
```

with:

```java
p.introduce();
```

The first establishes the object.

The second operates on an already existing object.

```text
                 OBJECT LIFECYCLE

        doesn't exist
              │
              ↓
       new Patient(...)
              │
              ↓
        construction
              │
              ↓
       initialized object
              │
              ↓
       p.introduce()
              │
              ↓
       object behavior
```

This distinction will become important when studying the full object lifecycle.

---

# 21. Real-World Domain Modeling

Consider a hospital appointment.

A meaningful appointment may require:

```text
patient
doctor
appointmentTime
```

and may start with:

```text
status = SCHEDULED
```

Conceptually:

```text
new Appointment(patient, doctor, time)
                  │
                  ↓
             constructor
                  │
                  ↓
       establish initial state
                  │
                  ↓
             Appointment
```

The constructor expresses an important domain rule:

> **What information is necessary for an appointment to exist?**

This is one reason constructors are important in domain modeling.

---

# 22. Construction vs Mutation

A useful design distinction is:

```text
CONSTRUCTION
    ↓
establish initial state

MUTATION
    ↓
change existing state
```

For example:

```java
Appointment appointment =
    new Appointment(patient, doctor, time);
```

creates the appointment.

Later:

```java
appointment.reschedule(newTime);
```

changes an existing appointment.

Conceptually:

```text
CREATE
  ↓
valid initial state
  ↓
EXISTING OBJECT
  ↓
controlled state changes
```

This distinction prepares the way for encapsulation.

---

# 23. Common Misconceptions

### Misconception 1

> A constructor is a method.

Not exactly.

It is a special mechanism associated with object construction and has different language rules.

---

### Misconception 2

> Constructors return objects.

Conceptually, object creation results in a reference to the new object, but a constructor itself does not have a return type and is not called like a normal returning method.

---

### Misconception 3

> Java always provides a no-argument constructor.

Incorrect.

Java provides a default constructor only when **no constructor is declared**.

---

### Misconception 4

> `this` always means the constructor.

Incorrect.

`this` refers to the current object and can also be used for constructor invocation through `this(...)`.

---

### Misconception 5

> Constructors should always accept every possible field.

Not necessarily.

A good constructor should require what is necessary to establish a meaningful valid object.

---

### Misconception 6

> Constructor validation is unnecessary because setters can validate later.

That can allow an invalid object to exist temporarily.

A stronger design often establishes valid state as early as possible.

---

# 24. Engineering Principles

### Principle 1 — Construct meaningful objects

Ask:

> What must be known for this object to meaningfully exist?

---

### Principle 2 — Establish invariants early

If:

```text
age >= 0
```

must always be true, don't casually construct:

```text
age = -10
```

and hope someone fixes it later.

---

### Principle 3 — Avoid duplicated initialization logic

When multiple constructors share initialization logic, consider constructor chaining.

```java
this(...);
```

---

### Principle 4 — Construction should reflect domain requirements

If an appointment fundamentally requires a doctor and patient, the constructor should make that requirement visible.

---

### Principle 5 — Don't confuse convenience with good design

A no-argument constructor is not automatically better.

Ask:

> Does allowing an object to exist without required information make sense?

---

# 25. Experiment Matrix

| Experiment | Question                                    | Main Concept        |
| ---------- | ------------------------------------------- | ------------------- |
| 01         | How is initial state established?           | Constructor         |
| 02         | What happens without `this`?                | Current object      |
| 03         | What happens when no constructor exists?    | Default constructor |
| 04         | What happens after declaring a constructor? | Constructor rules   |
| 05         | Can a class have multiple constructors?     | Overloading         |
| 06         | Can constructors delegate to each other?    | `this(...)`         |
| 07         | Can invalid state be rejected?              | Invariants          |

---

# 26. Debugging Protocol

When constructor behavior is unexpected:

```text
OBSERVE
   ↓
What object state actually exists?
   ↓
REPRODUCE
   ↓
Can I create the problem consistently?
   ↓
ISOLATE
   ↓
Is the problem in construction or later mutation?
   ↓
HYPOTHESIS
   ↓
What should the constructor have done?
   ↓
TEST
   ↓
Change one thing
   ↓
ROOT CAUSE
   ↓
Why did the object receive this state?
   ↓
FIX
   ↓
Correct initialization
   ↓
VERIFY
   ↓
Test other construction paths
```

The goal is to debug the **object model**, not just the line that produced the error.

---

# 27. Mastery Questions

I should be able to answer these without looking at the code:

1. What problem does a constructor solve?
2. What happens conceptually when `new Patient(...)` executes?
3. Why must a constructor have the same name as its class?
4. Why doesn't a constructor have a return type?
5. How is a constructor different from a method?
6. What does `this.name = name` mean?
7. What happens if `this` is removed when parameter and field names are identical?
8. When does Java provide a default constructor?
9. What happens to the default constructor when I declare another constructor?
10. What is constructor overloading?
11. What is constructor chaining?
12. What does `this(...)` mean?
13. Why can constructor validation be important?
14. What is an object invariant?
15. Why is construction an important design boundary?
16. What information should a real-world object's constructor require?

---

# 28. Mastery Challenge

Without looking at previous examples, design:

```text
BankAccount
```

with:

```text
State:
    accountNumber
    owner
    balance
```

Requirements:

```text
1. accountNumber is required.
2. owner is required.
3. initial balance cannot be negative.
4. a valid object must never start with an invalid balance.
```

Then answer:

```text
What should the constructor accept?

What should it validate?

What should happen when invalid input is supplied?

What state should exist immediately after construction?
```

Don't begin with syntax.

Start with the object model:

```text
BankAccount
      │
      ↓
required information
      │
      ↓
constructor
      │
      ↓
validation
      │
      ├── valid → object exists
      │
      └── invalid → construction rejected
```

---

# 29. Learning Record

For this lab, document:

### Question

How does Java establish an object's initial state?

### Hypothesis

What did I believe would happen before running the experiment?

### Experiment

What code did I execute?

### Observation

What actually happened?

### Explanation

Why did it happen?

### Break-It

What did I intentionally break?

### Debugging

How did I isolate the cause?

### Engineering Insight

What does this teach me about object design?

### Remaining Questions

What do I still need to investigate?

---

# 30. Final Mental Model

The most important model from this lab is:

```text
                         CLASS
                           │
                           │ new
                           ↓
                    object creation
                           │
                           ↓
                     initialization
                           │
                           ↓
                     CONSTRUCTOR
                           │
                  ┌────────┴────────┐
                  ↓                 ↓
              initialize         validate
                 state             state
                  │                 │
                  └────────┬────────┘
                           ↓
                   VALID OBJECT
                           │
                           ↓
                      METHODS
                           │
                           ↓
                    controlled behavior
```

And:

```text
this.field
    ↓
field belonging to current object

this(...)
    ↓
invoke another constructor
```

---

# 31. Key Principles to Internalize

> **A constructor establishes the initial state of a newly created object.**

> **A constructor is not an ordinary method; it participates in object construction.**

> **`this` identifies the current object and can also delegate to another constructor through `this(...)`.**

> **If no constructor is declared, Java provides a default no-argument constructor; once a constructor is declared, that automatic constructor is no longer provided.**

> **A good constructor should help establish a meaningful and valid object state.**

---

# 32. Connection to the OOP Roadmap

You are building the model progressively:

```text
01 Object Model
      │
      ├── class
      ├── object
      ├── identity
      ├── state
      └── behavior
             │
             ↓
02 State & Behavior
             │
             ↓
03 Constructors
             │
             ├── initialization
             ├── valid initial state
             ├── invariants
             └── object creation boundary
                    │
                    ↓
04 Instance vs Class
                    │
                    ├── instance members
                    ├── static members
                    └── shared state
                           │
                           ↓
05 References & Identity
```

This foundation prepares you for the next major question:

> **If multiple variables can refer to objects, what exactly is a reference, how is object identity determined, and why can two variables affect the same object?**

That is the purpose of:

**`04?` / `05 — References & Identity`**, depending on the final numbering you keep in your folder structure.

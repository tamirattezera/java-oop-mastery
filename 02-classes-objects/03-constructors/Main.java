class Patient {
    String name;
    int age;

    Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void introduce() {
        System.out.println(
            "I am " + name + ", age " + age
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
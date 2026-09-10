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

        p1.name = "Eden";
        p1.age = 25;

        p2.name = "Marta";
        p2.age = 31;

        p1.introduce();
        p2.introduce();
    }
}
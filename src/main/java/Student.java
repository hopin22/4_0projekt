public class Student {

    private String name;
    private int age;

    // Konstruktor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter dla imienia
    public String getName() {
        return name;
    }

    // Getter dla wieku
    public int getAge() {
        return age;
    }

    // Nadpisana metoda toString()
    @Override
    public String toString() {
        return name + " " + age;
    }

    // Metoda parse - zamiana Stringa na obiekt Student
    public static Student parse(String str) {
        String[] data = str.split(" ");
        if (data.length != 2) {
            return new Student("Parse Error", -1);
        }
        return new Student(data[0], Integer.parseInt(data[1]));
    }
}


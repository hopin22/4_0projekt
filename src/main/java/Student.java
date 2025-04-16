public class Student {

    private String name;
    private int age;
    private String email;
    private String phoneNumber;

    // Konstruktor z dwoma parametrami
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Konstruktor z czterema parametrami (name, age, email, phoneNumber)
    public Student(String name, int age, String email, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Gettery
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Metoda toString, która zwraca dane studenta w odpowiednim formacie (z przecinkami)
    @Override
    public String toString() {
        return name + "," + age + "," + email + "," + phoneNumber;
    }

    // Parse metoda do konwersji z tekstu na obiekt Student
    public static Student parse(String str) {
        // Usuwamy niepotrzebne fragmenty tekstu, np. "Age: "
        str = str.replaceAll("Age: ", "").trim();

        String[] data = str.split(",");
        if (data.length != 4) {
            return null;  // Zwracamy null, jeśli dane są niekompletne
        }

        try {
            int age = Integer.parseInt(data[1].trim());
            return new Student(data[0], age, data[2], data[3]);
        } catch (NumberFormatException e) {
            // Jeśli parsowanie liczby się nie uda, zwracamy null
            return null;
        }
    }
}

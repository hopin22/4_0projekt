import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Student {

    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, String email, String phoneNumber, String dateOfBirth) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDateOfBirth() { return dateOfBirth; }

    @Override
    public String toString() {
        return name + "," + age + "," + email + "," + phoneNumber + "," + dateOfBirth;
    }

    public static Student parse(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) return null;
        try {
            String name = parts[0].trim();
            int age = Integer.parseInt(parts[1].trim());
            String email = parts[2].trim();
            String phone = parts[3].trim();
            String dob = parts[4].trim();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dob, fmt);
            int year = date.getYear();
            if (year < 1 || year > 3000) return null;

            return new Student(name, age, email, phone, dob);
        } catch (DateTimeParseException | NumberFormatException e) {
            return null;
        }
    }
}

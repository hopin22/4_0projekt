import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Student {
    private String name; private String surname; private int age;
    private String email; private String phoneNumber; private String dateOfBirth;

    public Student(String name, String surname, int age) {
        this.name = name; this.surname = surname; this.age = age;
    }
    public Student(String name, String surname, int age, String email, String phone, String dob) {
        this.name = name; this.surname = surname; this.age = age;
        this.email = email; this.phoneNumber = phone; this.dateOfBirth = dob;
    }
    public String getName() { return name; } public String getSurname() { return surname; }
    public int getAge() { return age; } public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; } public String getDateOfBirth() { return dateOfBirth; }
    @Override
    public String toString() {
        return name+","+surname+","+age+","+email+","+phoneNumber+","+dateOfBirth;
    }
    public static Student parse(String line) {
        String[] p = line.split(","); if (p.length != 6) return null;
        String n=p[0].trim(), s=p[1].trim(), a=p[2].trim(), e=p[3].trim(), ph=p[4].trim(), d=p[5].trim();
        if (n.isEmpty()||s.isEmpty()) return null;
        int ag; try { ag=Integer.parseInt(a); if (ag<1||ag>150) return null; } catch (Exception ex) { return null; }
        if (!e.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) return null;
        if (!ph.matches("\\+?\\d{7,15}")) return null;
        try { LocalDate dt = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int y=dt.getYear(); if (y<1||y>3000) return null;
        } catch (DateTimeParseException ex) { return null; }
        return new Student(n, s, ag, e, ph, d);
    }
}


import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

    public void addStudent(Student student) throws IOException {
        if (student.getName() == null || student.getName().isEmpty() ||
            student.getAge() <= 0 || student.getEmail() == null || student.getPhoneNumber() == null ||
            student.getDateOfBirth() == null || student.getDateOfBirth().isEmpty()) {
            throw new IllegalArgumentException("Student data is invalid.");
        }
        try (BufferedWriter b = new BufferedWriter(new FileWriter("db.txt", true))) {
            b.append(student.toString());
            b.newLine();
        }
    }

    public Collection<Student> getStudents() throws IOException {
        var ret = new ArrayList<Student>();
        try (BufferedReader reader = new BufferedReader(new FileReader("db.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student st = Student.parse(line.trim());
                    if (st != null) ret.add(st);
                }
            }
        }
        return ret;
    }

    public Student findStudentByName(String name) throws IOException {
        for (Student student : getStudents()) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}
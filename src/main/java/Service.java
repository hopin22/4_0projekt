import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service {

    public void addStudent(Student student) throws IOException {
        if (student.getName() == null || student.getName().isEmpty() ||
            student.getSurname() == null || student.getSurname().isEmpty() ||
            student.getAge() <= 0 || student.getEmail() == null || student.getPhoneNumber() == null ||
            student.getDateOfBirth() == null || student.getDateOfBirth().isEmpty()) {
            throw new IllegalArgumentException("Nieprawidłowe dane studenta.");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("db.txt", true))) {
            bw.append(student.toString());
            bw.newLine();
        }
    }

    public List<Student> getStudents() throws IOException {
        List<Student> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("db.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student st = Student.parse(line.trim());
                    if (st != null) list.add(st);
                }
            }
        }
        return list;
    }

    // Szukanie po imieniu i nazwisku (wszystkie dopasowania)
    public List<Student> findStudentsByFullName(String name, String surname) throws IOException {
        List<Student> result = new ArrayList<>();
        for (Student st : getStudents()) {
            if (st.getName().equalsIgnoreCase(name) && st.getSurname().equalsIgnoreCase(surname)) {
                result.add(st);
            }
        }
        return result;
    }

    /**
     * Zwraca pierwszego studenta o podanym imieniu i nazwisku,
     * lub null jeśli nie znaleziono.
     */
    public Student findStudentByFullName(String name, String surname) throws IOException {
        for (Student st : getStudents()) {
            if (st.getName().equalsIgnoreCase(name) && st.getSurname().equalsIgnoreCase(surname)) {
                return st;
            }
        }
        return null;
    }

    // Usuwanie N-tego dopasowania z pliku
    public boolean removeStudent(String name, String surname, int occurrence) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("db.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        int count = 0;
        boolean removed = false;
        List<String> updated = new ArrayList<>();
        for (String line : lines) {
            Student st = Student.parse(line.trim());
            if (!removed && st != null && st.getName().equalsIgnoreCase(name)
                && st.getSurname().equalsIgnoreCase(surname)) {
                if (count == occurrence) {
                    removed = true;
                    count++;
                    continue;
                }
                count++;
            }
            updated.add(line);
        }
        if (removed) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("db.txt"))) {
                for (String l : updated) {
                    bw.append(l);
                    bw.newLine();
                }
            }
        }
        return removed;
    }
}

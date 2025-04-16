import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Service {

  // Dodawanie studenta do pliku
  public void addStudent(Student student) throws IOException {
    // Sprawdzamy, czy dane studenta są poprawne
    if (student.getName() == null || student.getName().isEmpty() ||
        student.getAge() <= 0 || student.getEmail() == null || student.getPhoneNumber() == null) {
      throw new IllegalArgumentException("Student data is invalid.");
    }

    // Użycie try-with-resources zapewnia automatyczne zamknięcie strumieni
    try (BufferedWriter b = new BufferedWriter(new FileWriter("db.txt", true))) {
      b.append(student.toString());  // Poprawione wywołanie metody toString()
      b.newLine();  // Nowa linia po każdym studencie
    }
  }

  // Pobieranie studentów z pliku
  public Collection<Student> getStudents() throws IOException {
    var ret = new ArrayList<Student>();  // Kolekcja do przechowywania studentów

    // Użycie try-with-resources zapewnia automatyczne zamknięcie strumieni
    try (BufferedReader reader = new BufferedReader(new FileReader("db.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Jeśli linia nie jest pusta, próbujemy ją sparsować
        if (!line.trim().isEmpty()) {
          ret.add(Student.parse(line.trim()));  // Używamy metody parse (mała litera 'p')
        }
      }
    }
    return ret;  // Zwracamy listę studentów
  }

  // Metoda do znalezienia studenta po imieniu (możesz ją rozbudować)
  public Student findStudentByName(String name) throws IOException {
    var students = getStudents();
    for (Student student : students) {
      if (student.getName().equalsIgnoreCase(name)) {
        return student;
      }
    }
    return null;  // Jeśli nie znaleziono, zwróć null
  }
}

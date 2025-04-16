import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

  // Dodawanie studenta do pliku
  public void addStudent(Student student) throws IOException {
    var f = new FileWriter("db.txt", true);  // Otwieramy plik do dopisywania
    var b = new BufferedWriter(f);  // Bufor do zapisu
    b.append(student.toString());  // Poprawione wywołanie metody toString()
    b.newLine();  // Nowa linia po każdym studencie
    b.close();  // Zamykamy bufor
  }

  // Pobieranie studentów z pliku
  public Collection<Student> getStudents() throws IOException {
    var ret = new ArrayList<Student>();  // Kolekcja do przechowywania studentów
    var f = new FileReader("db.txt");  // Otwieramy plik do odczytu
    var reader = new BufferedReader(f);  // Bufor do odczytu
    String line = "";

    while (true) {
      line = reader.readLine();  // Czytamy linię
      if (line == null)  // Jeśli koniec pliku, przerywamy
        break;

      ret.add(Student.parse(line));  // Używamy metody parse (mała litera 'p')
    }

    reader.close();  // Zamykamy czytnik
    return ret;  // Zwracamy listę studentów
  }

  // Metoda do znalezienia studenta po imieniu (możesz ją rozbudować)
  public Student findStudentByName(String name) {
    return null;  // Na razie nie implementujemy tej funkcji
  }
}

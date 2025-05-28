
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Main {
    public static void main(String[] args) {
        try {
            Service s = new Service();
            Scanner scanner = new Scanner(System.in);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (true) {
                System.out.println("\n1. Dodaj nowego studenta");
                System.out.println("2. Wyświetl wszystkich studentów");
                System.out.println("3. Usuń studenta");
                System.out.println("4. Wyszukaj studenta");
                System.out.println("0. Wyjście");

                System.out.print("Wybierz opcję: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // czyścimy bufor

                switch (choice) {
                    case 1:
                        // ... (walidacja pól jak wcześniej)
                        String name;
                        while (true) {
                            System.out.print("Imię: ");
                            name = scanner.nextLine().trim();
                            if (name.isEmpty()) System.out.println("Imię nie może być puste!");
                            else break;
                        }
                        String surname;
                        while (true) {
                            System.out.print("Nazwisko: ");
                            surname = scanner.nextLine().trim();
                            if (surname.isEmpty()) System.out.println("Nazwisko nie może być puste!");
                            else break;
                        }
                        int age;
                        while (true) {
                            System.out.print("Wiek (1-150): ");
                            String input = scanner.nextLine().trim();
                            try {
                                age = Integer.parseInt(input);
                                if (age < 1 || age > 150) System.out.println("Wiek musi być z zakresu 1-150!");
                                else break;
                            } catch (NumberFormatException e) {
                                System.out.println("Wiek musi być liczbą całkowitą!");
                            }
                        }
                        String email;
                        while (true) {
                            System.out.print("Email: ");
                            email = scanner.nextLine().trim();
                            if (!email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) System.out.println("Niepoprawny format email!");
                            else break;
                        }
                        String phone;
                        while (true) {
                            System.out.print("Numer telefonu: ");
                            phone = scanner.nextLine().trim();
                            if (!phone.matches("\\+?\\d{7,15}")) System.out.println("Numer telefonu musi mieć 7-15 cyfr, opcjonalnie +!");
                            else break;
                        }
                        LocalDate dob;
                        while (true) {
                            System.out.print("Data urodzenia (yyyy-MM-dd): ");
                            String dobStr = scanner.nextLine().trim();
                            String[] parts = dobStr.split("-");
                            if (parts.length != 3) { System.out.println("Niepoprawny format! Użyj yyyy-MM-dd."); continue; }
                            try {
                                int y = Integer.parseInt(parts[0]);
                                int m = Integer.parseInt(parts[1]);
                                int d = Integer.parseInt(parts[2]);
                                if (y < 1 || y > 3000) { System.out.println("Rok 1-3000!"); continue; }
                                if (m < 1 || m > 12) { System.out.println("Miesiąc 1-12!"); continue; }
                                int maxD;
                                switch (m) {
                                    case 2: boolean leap=(y%400==0)||(y%4==0&&y%100!=0); maxD = leap?29:28; break;
                                    case 4: case 6: case 9: case 11: maxD = 30; break;
                                    default: maxD = 31;
                                }
                                if (d<1||d>maxD) { System.out.println("Dzień dla miesiąca "+m+" 1-"+maxD+"!"); continue; }
                                dob = LocalDate.of(y,m,d);
                                break;
                            } catch (NumberFormatException ne) {
                                System.out.println("Rok, miesiąc i dzień muszą być liczbami!");
                            } catch (DateTimeException de) {
                                System.out.println("Niepoprawna data! Spróbuj ponownie.");
                            }
                        }
                        s.addStudent(new Student(name, surname, age, email, phone, dob.toString()));
                        System.out.println("Student dodany!");
                        break;

                    case 2:
                        List<Student> all = s.getStudents();
                        if (all.isEmpty()) System.out.println("Brak studentów.");
                        else {
                            System.out.println("\nLista studentów:");
                            for (Student st : all) System.out.println(st);
                        }
                        break;

                    case 3:
                        // Usuń studenta
                        String dName;
                        while (true) {
                            System.out.print("Imię studenta do usunięcia: ");
                            dName = scanner.nextLine().trim();
                            if (dName.isEmpty()) System.out.println("Imię nie może być puste!");
                            else break;
                        }
                        String dSurname;
                        while (true) {
                            System.out.print("Nazwisko studenta do usunięcia: ");
                            dSurname = scanner.nextLine().trim();
                            if (dSurname.isEmpty()) System.out.println("Nazwisko nie może być puste!");
                            else break;
                        }
                        List<Student> matches = s.findStudentsByFullName(dName, dSurname);
                        if (matches.isEmpty()) {
                            System.out.println("Brak studentów o podanym imieniu i nazwisku.");
                        } else {
                            System.out.println("Znaleziono następujące konta:");
                            for (int i = 0; i < matches.size(); i++) {
                                System.out.println((i+1) + ". " + matches.get(i));
                            }
                            int idx;
                            while (true) {
                                System.out.print("Wybierz numer do usunięcia: ");
                                String in = scanner.nextLine().trim();
                                try {
                                    idx = Integer.parseInt(in);
                                    if (idx < 1 || idx > matches.size()) System.out.println("Nieprawidłowy numer!");
                                    else break;
                                } catch (NumberFormatException nfe) {
                                    System.out.println("Podaj numer jako liczbę!");
                                }
                            }
                            boolean removed = s.removeStudent(dName, dSurname, idx-1);
                            System.out.println(removed ? "Usunięto studenta." : "Błąd usuwania.");
                        }
                        break;

                    case 4:
                        // ... (wyszukiwanie jak wcześniej)
                        String sName;
                        while (true) {
                            System.out.print("Imię do wyszukania: ");
                            sName = scanner.nextLine().trim();
                            if (sName.isEmpty()) System.out.println("Imię nie może być puste!");
                            else break;
                        }
                        String sSurname;
                        while (true) {
                            System.out.print("Nazwisko do wyszukania: ");
                            sSurname = scanner.nextLine().trim();
                            if (sSurname.isEmpty()) System.out.println("Nazwisko nie może być puste!");
                            else break;
                        }
                        Student found = s.findStudentByFullName(sName, sSurname);
                        System.out.println(found!=null ? "Znaleziono: " + found : "Nie znaleziono studenta.");
                        break;

                    case 0:
                        System.out.println("Koniec programu."); return;
                    default:
                        System.out.println("Nieprawidłowa opcja.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.util.Scanner;

public class TablicaLiczb {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Wczytanie tablicy liczb całkowitych od użytkownika
        System.out.print("Ile liczb chcesz wprowadzić? ");
        int n = scanner.nextInt();
        int[] liczby = new int[n];

        System.out.println("Wprowadź " + n + " liczb całkowitych:");
        for (int i = 0; i < n; i++) {
            liczby[i] = scanner.nextInt();
        }

        // 2. Znalezienie największej i najmniejszej wartości
        int max = liczby[0];
        int min = liczby[0];

        for (int i = 1; i < liczby.length; i++) {
            if (liczby[i] > max) {
                max = liczby[i];
            }
            if (liczby[i] < min) {
                min = liczby[i];
            }
        }

        // 3. Obliczenie i wyświetlenie różnicy
        int roznica = max - min;
        System.out.println("Różnica między największą a najmniejszą liczbą wynosi: " + roznica);
    }
}

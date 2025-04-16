import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            Service s = new Service();
            Scanner scanner = new Scanner(System.in);

            // Wprowadzanie studentów za pomocą skanera
            while (true) {
                System.out.println("\n1. Dodaj nowego studenta");
                System.out.println("2. Wyswietl wszystkich studentow");
                System.out.println("0. Wyjsc");

                System.out.print("Wybierz opcje: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // To clear the buffer

                switch (choice) {
                    case 1:
                        // Dodawanie nowego studenta
                        System.out.print("Imie: ");
                        String name = scanner.nextLine();

                        System.out.print("Wiek(podaj w liczbie calkowitej): ");
                        int age = scanner.nextInt();
                        scanner.nextLine();  // Clears buffer after age input

                        // Zbieranie dodatkowych danych
                        System.out.print(" email: ");
                        String email = scanner.nextLine();

                        System.out.print("numer telefonu: ");
                        String phoneNumber = scanner.nextLine();

                        // Dodajemy nowego studenta do bazy
                        s.addStudent(new Student(name, age, email, phoneNumber));
                        System.out.println("Student dodany!");
                        break;

                    case 2:
                        // Wyświetlanie wszystkich studentów
                        var students = s.getStudents();
                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            System.out.println("\nList of students:");
                            for (Student current : students) {
                                if (current != null) {  // Sprawdzamy, czy student nie jest null
                                    System.out.println(current.toString());
                                }
                            }
                        }
                        break;

                    case 0:
                        // Zakończenie programu
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

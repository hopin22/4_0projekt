import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            Service s = new Service();
            Scanner scanner = new Scanner(System.in);

            // Wprowadzanie studentów za pomocą skanera
            while (true) {
                // Menu opcji
                System.out.println("\n1. Add a new student");
                System.out.println("2. Display all students");
                System.out.println("3. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Oczyszczanie bufora po wprowadzeniu liczby

                switch (choice) {
                    case 1:
                        // Dodawanie nowego studenta
                        System.out.print("Enter student's name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter student's age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();  // Oczyszczanie bufora po wprowadzeniu liczby

                        // Dodanie studenta do listy
                        s.addStudent(new Student(name, age));
                        System.out.println("Student added!");
                        break;

                    case 2:
                        // Wyświetlanie wszystkich studentów
                        var students = s.getStudents();
                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            System.out.println("\nList of students:");
                            for (Student current : students) {
                                System.out.println(current.toString());  // Wyświetlanie imienia i wieku
                            }
                        }
                        break;

                    case 3:
                        // Zakończenie programu
                        System.out.println("Exiting...");
                        return;  // Kończy program

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

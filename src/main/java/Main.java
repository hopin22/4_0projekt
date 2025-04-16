/*
Kod bazowy programu Commit4_0: 
• Program dodaje do prostej bazy danych (pliku db.txt) dane odnośnie Studentów.
• Studenci dodawani są w klasie Main.
• Wszyscy studenci są wypisywani na końcu klasy Main.
• Klasa Service obsługuje odczyt i zapis do pliku bazy danych.
• Klasa Student reprezentuje pojedynczego studenta (Imię, Wiek).
*/

import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            Service s = new Service();
            Scanner scanner = new Scanner(System.in);

            // Wprowadzanie studentów za pomocą skanera
            while (true) {
                System.out.print("Enter student's name (or type 'exit' to stop): ");
                String name = scanner.nextLine();

                // Jeśli użytkownik wpisze "exit", zakończ program
                if (name.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter student's age: ");
                int age = scanner.nextInt();
                scanner.nextLine();  // Czyści bufor po wprowadzeniu liczby

                // Dodajemy nowego studenta do listy
                s.addStudent(new Student(name, age));
            }

            // Wyświetlanie studentów
            var students = s.getStudents();
            for (Student current : students) {
                System.out.println(current.ToString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

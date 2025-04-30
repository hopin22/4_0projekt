
import java.io.IOException;
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
                System.out.println("4. Wyszukaj studenta");
                System.out.println("0. Wyjście");

                System.out.print("Wybierz opcję: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
             
                        String name;
                        while (true) {
                            System.out.print("Imię: ");
                            name = scanner.nextLine().trim();
                            if (name.isEmpty()) {
                                System.out.println("Imię nie może być puste!");
                            } else {
                                break;
                            }
                        }

                        int age;
                        while (true) {
                            System.out.print("Wiek (liczba całkowita): ");
                            String ageInput = scanner.nextLine().trim();
                            try {
                                age = Integer.parseInt(ageInput);
                                if (age <= 0 || age > 150) {
                                    System.out.println("Wiek musi być liczbą z zakresu 1-150!");
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Wiek musi być liczbą całkowitą!");
                            }
                        }

                        String email;
                        while (true) {
                            System.out.print("Email: ");
                            email = scanner.nextLine().trim();
                            if (!email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
                                System.out.println("Niepoprawny format email!");
                            } else {
                                break;
                            }
                        }

                        String phone;
                        while (true) {
                            System.out.print("Numer telefonu: ");
                            phone = scanner.nextLine().trim();
                            if (!phone.matches("\\+?\\d{7,15}")) {
                                System.out.println("Numer telefonu musi składać się z 7-15 cyfr, opcjonalnie z prefiksem +!");
                            } else {
                                break;
                            }
                        }

                        LocalDate dob;
                        while (true) {
                            System.out.print("Data urodzenia (format yyyy-MM-dd): ");
                            String dobStr = scanner.nextLine().trim();
                            String[] parts = dobStr.split("-");
                            if (parts.length != 3) {
                                System.out.println("Niepoprawny format! Użyj yyyy-MM-dd.");
                                continue;
                            }
                            try {
                                int year = Integer.parseInt(parts[0]);
                                int month = Integer.parseInt(parts[1]);
                                int day = Integer.parseInt(parts[2]);

                                if (year < 1 || year > 3000) {
                                    System.out.println("Rok musi być w zakresie od 1 do 3000!");
                                    continue;
                                }
                                if (month < 1 || month > 12) {
                                    System.out.println("Miesiąc musi być w zakresie od 1 do 12!");
                                    continue;
                                }
                                int maxDay;
                                switch (month) {
                                    case 2:
                                        boolean leap = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
                                        maxDay = leap ? 29 : 28;
                                        break;
                                    case 4: case 6: case 9: case 11:
                                        maxDay = 30;
                                        break;
                                    default:
                                        maxDay = 31;
                                }
                                if (day < 1 || day > maxDay) {
                                    System.out.println("Dla miesiąca " + month + " dzień musi być w zakresie od 1 do " + maxDay + "!");
                                    continue;
                                }
                                dob = LocalDate.of(year, month, day);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Rok, miesiąc i dzień muszą być liczbami!");
                            } catch (DateTimeException e) {
                                System.out.println("Niepoprawna data! Spróbuj ponownie.");
                            }
                        }

                        s.addStudent(new Student(name, age, email, phone, dob.toString()));
                        System.out.println("Student dodany!");
                        break;

                    case 2:
                        var students = s.getStudents();
                        if (students.isEmpty()) {
                            System.out.println("Brak studentów w bazie.");
                        } else {
                            System.out.println("\nLista studentów:");
                            for (Student st : students) {
                                System.out.println(st);
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Podaj imię studenta do wyszukania: ");
                        String searchName = scanner.nextLine().trim();
                        Student found = s.findStudentByName(searchName);
                        if (found != null) {
                            System.out.println("Znaleziono: " + found);
                        } else {
                            System.out.println("Nie znaleziono studenta o imieniu '" + searchName + "'.");
                        }
                        break;

                    case 0:
                        System.out.println("Koniec programu.");
                        return;

                    default:
                        System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
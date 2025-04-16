public class Student {

  private String name;
  private int age;
  private String email;
  private String phoneNumber;

  // Zmieniony konstruktor, aby przyjmował nowe dane
  public Student(String name, int age, String email, String phoneNumber) {
    this.name = name;
    this.age = age;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  // Getter dla imienia
  public String getName() {
    return name;
  }

  // Getter dla wieku
  public int getAge() {
    return age;
  }

  // Getter dla e-maila
  public String getEmail() {
    return email;
  }

  // Getter dla numeru telefonu
  public String getPhoneNumber() {
    return phoneNumber;
  }

  // Poprawiona metoda toString, aby wyświetlała wszystkie dane
  @Override
  public String toString() {
    return "Name: " + name + ", Age: " + age + ", Email: " + email + ", Phone: " + phoneNumber;
  }

  // Parser dla tworzenia obiektu Student z tekstu
  public static Student parse(String str) {
    String[] data = str.split(",");
    if (data.length != 4) {
      return new Student("Parse Error", -1, "Invalid email", "Invalid phone");
    }
    return new Student(data[0].trim(), Integer.parseInt(data[1].trim()), data[2].trim(), data[3].trim());
  }
}

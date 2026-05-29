import java.util.Scanner;

class StudentView {

    Scanner sc = new Scanner(System.in);

    void showMenu() {

        System.out.println("\n--- Student Management Menu ---");

        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student Mark");
        System.out.println("5. Delete Student");
        System.out.println("6. Show Topper");
        System.out.println("7. Exit");
    }

    int getChoice() {

        System.out.print("Enter choice: ");
        return sc.nextInt();
    }

    int getId() {

        System.out.print("Enter ID: ");
        return sc.nextInt();
    }

    int getMark() {

        System.out.print("Enter Mark: ");
        return sc.nextInt();
    }

    int getAttendance() {

        System.out.print("Enter Attendance %: ");
        return sc.nextInt();
    }

    String getName() {

        sc.nextLine();
        System.out.print("Enter Name: ");
        return sc.nextLine();
    }

    void displayStudents(Student[] students, int count) {

        System.out.println("\nStudent Details:");

        for (int i = 0; i < count; i++) {

            System.out.println(
                    students[i].id + " | " +
                    students[i].name + " | " +
                    students[i].mark + " | Grade: " +
                    students[i].grade + " | Attendance: " +
                    students[i].attendance + "%"
            );
        }
    }

    void showMessage(String msg) {
        System.out.println(msg);
    }
}
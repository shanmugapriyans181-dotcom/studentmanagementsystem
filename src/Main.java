import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {

        DBConnection.getConnection();

        System.out.println(
                "Database Connected");

        StudentController controller =
                new StudentController(20);

        StudentView view =
                new StudentView();

        while (true) {

            view.showMenu();

            int choice =
                    view.getChoice();

            switch (choice) {

                // ADD STUDENT

                case 1:

                    int id =
                            view.getId();

                    // CHECK DUPLICATE ID
                    // FROM DATABASE

                    try {

                        Connection con =
                                DBConnection.getConnection();

                        String query =
                                "SELECT * FROM students WHERE id = ?";

                        PreparedStatement ps =
                                con.prepareStatement(query);

                        ps.setInt(1, id);

                        ResultSet rs =
                                ps.executeQuery();

                        // DUPLICATE FOUND

                        if (rs.next()) {

                            view.showMessage(
                                    "Student ID already exists");

                            break;
                        }
                    }

                    catch (Exception e) {

                        e.printStackTrace();
                    }

                    String name =
                            view.getName();

                    int mark =
                            view.getMark();

                    int attendance =
                            view.getAttendance();

                    String result =
                            controller.addStudent(
                                    id,
                                    name,
                                    mark,
                                    attendance
                            );

                    view.showMessage(result);

                    break;

                // DISPLAY STUDENTS

                case 2:

                    view.displayStudents(
                            controller.getStudents(),
                            controller.getCount()
                    );

                    break;

                // SEARCH STUDENT

                case 3:

                    int sid =
                            view.getId();

                    Student s =
                            controller.searchStudent(sid);

                    if (s != null) {

                        System.out.println(
                                s.id + " | " +
                                s.name + " | " +
                                s.mark + " | Grade: " +
                                s.grade
                        );
                    }

                    else {

                        view.showMessage(
                                "Student Not Found"
                        );
                    }

                    break;

                // UPDATE STUDENT

                case 4:

                    int uid =
                            view.getId();

                    int newMark =
                            view.getMark();

                    view.showMessage(
                            controller.updateStudent(
                                    uid,
                                    newMark
                            )
                    );

                    break;

                // DELETE STUDENT

                case 5:

                    int did =
                            view.getId();

                    view.showMessage(
                            controller.deleteStudent(did)
                    );

                    break;

                // SHOW TOPPER

                case 6:

                    Student top =
                            controller.getTopper();

                    if (top != null) {

                        System.out.println(
                                "Topper: " +
                                top.name +
                                " | Mark: " +
                                top.mark
                        );
                    }

                    else {

                        System.out.println(
                                "No Students Available"
                        );
                    }

                    break;

                // EXIT

                case 7:

                    System.out.println(
                            "Exiting..."
                    );

                    return;

                default:

                    System.out.println(
                            "Invalid Choice"
                    );
            }
        }
    }
}


import java.sql.Connection;
import java.sql.PreparedStatement;

class StudentController {

    Student[] students;
    int count;

    StudentController(int size) {

        students = new Student[size];
        count = 0;
    }

    // ADD STUDENT

    String addStudent(int id, String name,
                      int mark, int attendance) {

        // Check duplicate ID

        for (int i = 0; i < count; i++) {

            if (students[i].id == id) {

                return "Student ID already exists";
            }
        }

        Student s =
                new Student(id, name, mark, attendance);

        students[count++] = s;

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO students VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, s.id);
            ps.setString(2, s.name);
            ps.setInt(3, s.mark);
            ps.setInt(4, s.attendance);
            ps.setString(5,
                    String.valueOf(s.grade));

            ps.executeUpdate();

            return "Student Added Successfully";
        }

        catch (Exception e) {

            e.printStackTrace();
        }

        return "Error";
    }

    // DISPLAY STUDENTS

    Student[] getStudents() {

        return students;
    }

    int getCount() {

        return count;
    }

    // SEARCH STUDENT

    Student searchStudent(int id) {

        for (int i = 0; i < count; i++) {

            if (students[i].id == id)
                return students[i];
        }

        return null;
    }

    // UPDATE STUDENT

    String updateStudent(int id, int newMark) {

        Student s = searchStudent(id);

        if (s == null)
            return "Student Not Found";

        s.mark = newMark;
        s.grade = s.calculateGrade();

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "UPDATE students SET mark=?, grade=? WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, s.mark);
            ps.setString(2,
                    String.valueOf(s.grade));
            ps.setInt(3, s.id);

            ps.executeUpdate();

            return "Student Updated Successfully";
        }

        catch (Exception e) {

            e.printStackTrace();
        }

        return "Error";
    }

    // DELETE STUDENT

    String deleteStudent(int id) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM students WHERE id = ?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            // Remove from array also

            for (int i = 0; i < count; i++) {

                if (students[i].id == id) {

                    for (int j = i;
                         j < count - 1;
                         j++) {

                        students[j] =
                                students[j + 1];
                    }

                    count--;
                    break;
                }
            }

            if (rows > 0) {

                return "Student Deleted Successfully";
            }

            else {

                return "Student Not Found";
            }
        }

        catch (Exception e) {

            e.printStackTrace();
        }

        return "Error";
    }

    // TOPPER

    Student getTopper() {

        if (count == 0)
            return null;

        Student top = students[0];

        for (int i = 1; i < count; i++) {

            if (students[i].mark > top.mark) {

                top = students[i];
            }
        }

        return top;
    }
}

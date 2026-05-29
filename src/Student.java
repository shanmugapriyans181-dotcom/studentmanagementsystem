class Student {

    int id;
    String name;
    int mark;
    int attendance;
    char grade;

    Student(int id, String name, int mark, int attendance) {
        this.id = id;
        this.name = name;
        this.mark = mark;
        this.attendance = attendance;
        this.grade = calculateGrade();
    }

    char calculateGrade() {

        if (mark >= 90)
            return 'A';
        else if (mark >= 75)
            return 'B';
        else if (mark >= 50)
            return 'C';
        else
            return 'F';
    }
}
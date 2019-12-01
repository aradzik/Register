package com.company;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello");
        Student s = new Student();

        s.addStudent("name", "surname","1");
        s.addStudent("name", "surname","2");
        s.addStudent("name", "surname","3");
        s.addStudent("name", "surname","4");
        Grades g = new Grades(s.getStudentArrayList());
        Meeting m = new Meeting(s.getStudentArrayList());

        s.showStudent(0);
        s.showStudent(1);
        g.addGrade("1","3");
        g.addGrade("6","3");
        m.addMeeting("3","10.10.2019","true");
        m.addMeeting("6","10.10.2019","true");
        System.out.println(g.getGradesList().size());
        System.out.println(m.getMeetingsList().size());
    }
}

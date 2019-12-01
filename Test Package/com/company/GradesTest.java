package com.company;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.Parameters;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class GradesTest {

    public GradesTest() {
    }

    @Test
    public void shouldBeAbleToCreateGrades() {
        Grades grades = new Grades("8382", "5");
        assertNotNull(grades);
    }

    @Test
    public void shouldBeAbleToAddGrade() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Grades grade = new Grades(student.getStudentArrayList());
        // System.out.println(student.toString());

        grade.addGrade("8585", "4");
        grade.addGrade("8585", "5");
        grade.addGrade("8585", "5");
        grade.addGrade("8585", "3");
        int lastIndex = grade.getGradesList().size() - 1;
        assertEquals("3", grade.getGradesList().get(lastIndex).getGrade());
    }

    @Test
    public void shouldNotBeAbleToAddGrade() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Grades grade = new Grades(student.getStudentArrayList());

        grade.addGrade("8585", "4");
        grade.addGrade("8585", "5");
        grade.addGrade("8585", "5");
        grade.addGrade("8584", "3");
        int lastIndex = grade.getGradesList().size() - 1;
        assertEquals("5", grade.getGradesList().get(lastIndex).getGrade());
    }

    @Test
    public void shouldRemoveGrade() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Grades grade = new Grades(student.getStudentArrayList());

        grade.addGrade("8585", "4");
        grade.addGrade("8585", "5");
        grade.addGrade("8585", "5");
        grade.addGrade("8585", "1");
        int before = grade.getGradesList().size();
        grade.removeGrade("8585", "1");
        int after = grade.getGradesList().size();
        assertEquals(before, after);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotRemoveGrade() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Grades grade = new Grades(student.getStudentArrayList());

        grade.addGrade("8585", "4");
        grade.addGrade("8585", "5");
        grade.removeGrade("8581", "2");
    }

    @Test
    public void shouldReturnAverageStudent() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Grades grade = new Grades(student.getStudentArrayList());

        grade.addGrade("8585", "4");
        grade.addGrade("8585", "5");
        grade.addGrade("8585", "3");
        grade.addGrade("8585", "2");
        String result = "3.5";
        assertEquals(result, grade.calculateAverage("8585"));
    }

    @Test
    public void shouldReturnAllAverageStudent() {
        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Grades grade = new Grades(student.getStudentArrayList());

        grade.addGrade("8585", "4");
        grade.addGrade("8587", "5");
        grade.addGrade("8585", "3");
        grade.addGrade("8586", "2");
        String result = "3.5";
        assertEquals(result, grade.calculateAllAverage());
    }

    @Test
    public void shouldSaveToFile() throws FileNotFoundException {
        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Grades grade = new Grades(student.getStudentArrayList());

        grade.addGrade("8585", "4");
        grade.addGrade("8587", "5");
        grade.addGrade("8585", "3");
        grade.addGrade("8586", "2");
        grade.saveToFile();
    }

    @Test
    public void shouldReadFromFile() throws FileNotFoundException {
        Grades grades = new Grades();
        grades.readFromFile();
        String result = "[Grades{nrIndex='8585', grade='4', gradesList=[]}, Grades{nrIndex='8587', grade='5', gradesList=[]}, Grades{nrIndex='8585', grade='3', gradesList=[]}, Grades{nrIndex='8586', grade='2', gradesList=[]}, Grades{nrIndex='8585', grade='3', gradesList=[]}, Grades{nrIndex='8586', grade='2', gradesList=[]}]";
        assertEquals(grades.readFromFile(), result);
    }

    @Test
    public void getNrIndex() {
        Grades grades = new Grades("123", "5");
        assertEquals("123", grades.getNrIndex());
    }

    @Test
    public void getGrade() {
        Grades grades = new Grades("123", "5");
        assertEquals("5", grades.getGrade());
    }

    @Test
    @Parameters({"2,2", "3,3", "4,4"})
    public void setGrade(String mark, String result) {
        Grades grade = new Grades();
        grade.setGrade(mark);
        assertEquals(result, grade.getGrade());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters({"2.5", "1", "5.56"})
    public void setWrongGrade(String mark) {
        Grades grade = new Grades();
        grade.setGrade(mark);
    }
}
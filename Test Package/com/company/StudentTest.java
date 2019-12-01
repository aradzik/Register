package com.company;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class StudentTest {

    public StudentTest() {
    }

    @Test
    public void shouldSaveToFile() throws FileNotFoundException {
        Student student = new Student();
        student.addStudent("Anna", "Kowalska", "8585");
        student.addStudent("Marek", "Nowak", "8586");
        student.saveToFile();
    }

    @Test
    public void shouldRemoveStudent() {
        Student student = new Student();
        student.addStudent("name", "surname", "index");
        student.addStudent("name", "surname", "index1");
        assertFalse(student.removeStudent("index1"));
    }

    @Test
    public void shouldNotRemoveStudent() {
        Student student = new Student();
        student.addStudent("name", "surname", "index");
        student.addStudent("name", "surname", "index1");
        assertFalse(student.removeStudent("index5"));
    }

    @Test
    public void shouldAddStudentToList() {

        Student student = new Student();
        student.addStudent("ola", "radzik", "80808");
        int lastIndex = student.getStudentArrayList().size() - 1;
        assertEquals("ola", student.getStudentArrayList().get(lastIndex).getName());
    }

    @Test
    public void shouldNotAddTheSameStudentToList() {

        Student student = new Student();
        student.addStudent("ola", "radzik", "80808");
        student.addStudent("ela", "radzik", "80808");
        int lastIndex = student.getStudentArrayList().size() - 1;
        assertEquals("ela", student.getStudentArrayList().get(lastIndex).getName());
    }

    @Test
    public void shouldBeAbleToCreateStudent() {
        Student student = new Student("Jan", "Kowalski", "83821");
        assertNotNull(student);
    }

    @Test
    public void shouldReturnName() {
        Student student = new Student("Jan", "Kowalski", "83821");
        assertEquals("Jan", student.getName());
    }

    @Test
    public void shouldReturnSurname() {
        Student student = new Student("Jan", "Kowalski", "83821");
        assertEquals("Kowalski", student.getSurname());
    }

    @Test
    public void shouldReturnIndex() {
        Student student = new Student("Jan", "Kowalski", "83821");
        assertEquals("83821", student.getNrIndex());
    }

    @Test
    public void shouldSetName() {
        Student student = new Student("Jan", "Kowalski", "83821");
        student.setName("Marek");
        assertEquals("Marek", student.getName());
    }

    @Test
    public void shouldSetSurname() {
        Student student = new Student("Jan", "Kowalski", "83821");
        student.setSurname("Nowak");
        assertEquals("Nowak", student.getSurname());
    }

    @Test
    public void shouldSetIndex() {
        Student student = new Student("Jan", "Kowalski", "83821");
        student.setNrIndex("88888");
        assertEquals("88888", student.getNrIndex());
    }
}
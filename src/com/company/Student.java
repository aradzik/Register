package com.company;

import java.io.*;
import java.util.ArrayList;

public class Student {

    String name;
    String surname;
    String nrIndex;

    public ArrayList<Student> studentArrayList = new ArrayList<>();

    public Student() {
    }

    public ArrayList<Student> getStudentArrayList() {

        return studentArrayList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nrIndex='" + nrIndex + '\'' +
                '}';
    }


    public Student(String name, String surname, String nrIndex) {
        this.name = name;
        this.surname = surname;
        this.nrIndex = nrIndex;
    }

    public boolean removeStudent(String nrIndex) {
        int index = 0;

        for (int i = 0; i < studentArrayList.size(); i++) {
            if (!nrIndex.equals(studentArrayList.get(i).getNrIndex())) {//sprawdzenie czy jest taki indeks
                System.out.println("Nie ma takiego");
                return false;
            }
            if (studentArrayList.get(i).getNrIndex().equals(nrIndex)) { //jak jest to usun
                index = studentArrayList.indexOf(studentArrayList.get(i));
                studentArrayList.remove(index);
            }
        }

        int size = studentArrayList.size();
        for (int i = 0; i <= size - 1; i++) { //sprwdzay czy alej istnieje ten indeks
            if (nrIndex.equals(studentArrayList.get(i).getNrIndex())) {
                System.out.println("Student o podanym indeksie istnieje");
                return true;
            } else {
                System.out.println("Usunięto delikwenta");
                return false;
            }
        }
        return false;
    }

    public void addStudent(String name, String surname, String nrIndex) {

        Student student = new Student(name, surname, nrIndex);
        if (studentArrayList.size() == 0) {
            studentArrayList.add(student);
        } else {
            int size = studentArrayList.size();
            for (int i = 0; i <= size - 1; i++) {
                if (nrIndex.equals(studentArrayList.get(i).getNrIndex())) {
                    System.err.println("Student o podanym indeksie istnieje");
                }
            }
            studentArrayList.add(student);
        }
    }

    public void saveToFile() throws FileNotFoundException {

        int size = studentArrayList.size();
        String toSave = "";
        for (int i = 0; i < size; i++) {
            toSave += studentArrayList.get(i).getName() + ", "
                    + studentArrayList.get(i).getSurname() + ", "
                    + studentArrayList.get(i).getNrIndex() + ";";
        }
        PrintWriter writer = new PrintWriter("Students.txt");
        writer.println(toSave);
        writer.close();
    }

    public int showSizeList() {
        return studentArrayList.size();
    }

    public void showStudent(int number) {
        System.out.println(studentArrayList.get(number).toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNrIndex() {
        return nrIndex;
    }

    public void setNrIndex(String nrIndex) {
        this.nrIndex = nrIndex;
    }


}

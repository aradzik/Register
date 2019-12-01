package com.company;

import java.io.*;
import java.util.ArrayList;


public class Grades {
    private String nrIndex, grade;

    private ArrayList<Grades> gradesList = new ArrayList<>();

    public ArrayList<Grades> getGradesList() {

        return gradesList;
    }

    private ArrayList<Student> studentArray;

    public Grades(ArrayList<Student> student) {
        this.studentArray = student;
    }

    public Grades() {
    }

    public Grades(String nrIndex) {
        this.nrIndex = nrIndex;
    }

    public Grades(String nrIndex, String grade) {
        this.nrIndex = nrIndex;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "nrIndex='" + nrIndex + '\'' +
                ", grade='" + grade + '\'' +
                ", gradesList=" + gradesList +
                '}';
    }

    public void addGrade(String nrIndex, String grade) {

        Grades grades = new Grades(nrIndex, grade);
        for (Student student : studentArray) {

            if (student.getNrIndex().equals(nrIndex)) {
                gradesList.add(grades);
            }
        }

//        for (int i = 0; i < size; i++) {
//            if (studentArray.get(i).getNrIndex() == nrIndex) {
//                gradesList.add(grades);
//            } else if (studentArray.get(i).getNrIndex() != nrIndex) {
//                //throw new IllegalArgumentException("Brak takiego indeksu");
//                // System.out.println("Brak indeksu");
//            }
//        }
    }

    public void removeGrade(String nrIndex, String grade) {
        if (gradesList.isEmpty()) {
            System.out.println("Brak ocen");
        } else {

            int size = gradesList.size() - 1;
            for (int i = 0; i < size; i++) {

                if (gradesList.get(i).getNrIndex() != nrIndex) {
                    throw new IllegalArgumentException("Brak takiego indeksu");
                } else if (gradesList.get(i).getNrIndex() == nrIndex) {
                    if (gradesList.get(i).getGrade() == grade) {
                        gradesList.remove(i);
                    }
                }
            }
        }
    }

    public String calculateAverage(String nrIndex) {

        double result = 0;
        double suma = 0;
        int size = gradesList.size();
        if (gradesList.isEmpty()) {
            System.out.println("Brak ocen");
        } else {
            for (int i = 0; i < size; i++) {
                if (gradesList.get(i).nrIndex == nrIndex) {
                    suma += Double.parseDouble(gradesList.get(i).getGrade());
                }
            }
        }
        result = suma / size;
        return String.valueOf(result);
    }

    public String calculateAllAverage() {

        double result = 0;
        double suma = 0;
        int size = gradesList.size();
        if (gradesList.isEmpty()) {
            System.out.println("Brak ocen");
        } else {

            for (int i = 0; i < size; i++) {
                suma += Double.parseDouble(gradesList.get(i).getGrade());
            }
        }
        result = suma / size;
        return String.valueOf(result);
    }

    public void setNrIndex(String nrIndex) {
        this.nrIndex = nrIndex;
    }

    public void saveToFile() throws FileNotFoundException {

        int size = gradesList.size();
        String toSave = "";
        for (int i = 0; i < size; i++) {
            toSave += gradesList.get(i).getNrIndex() + ","
                    + gradesList.get(i).getGrade() + ";";
        }
        PrintWriter writer = new PrintWriter("Grades.txt");
        writer.println(toSave);
        writer.close();
    }

    public String readFromFile() throws FileNotFoundException {

        try (FileReader reader = new FileReader("Grades.txt");
             BufferedReader br = new BufferedReader(reader)) {

            String line = "";

            while ((line = br.readLine()) != null) {

                String[] tab = line.split(";");
                int i = 0;
                String[] tablica = new String[100];
                //Grades grades = new Grades();
                for (String b : tab) {
                    String[] bb = b.split(",");
                    for (String a : bb) {
                        tablica[i] = a;
                        if (i == gradesList.size()) {
                            i = 0;
                        }
                        i++;
                    }

                }
                for (int j = 0; j < i - 1; j += 2) {
                    System.out.println(tablica[j] + ", " + tablica[j + 1]);
                    Grades grades = new Grades();
                    grades.setNrIndex(tablica[j]);
                    grades.setGrade(tablica[j + 1]);

                    gradesList.add(grades);
                }

            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        // System.out.println(gradesList.toString());
        return gradesList.toString();
    }

    public String getNrIndex() {
        return nrIndex;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {

        if (grade.equals("2") ||
                grade.equals("3") ||
                grade.equals("3.5") ||
                grade.equals("4") ||
                grade.equals("4.5") ||
                grade.equals("5")) {
            this.grade = grade;
        } else throw new IllegalArgumentException("");
    }


}

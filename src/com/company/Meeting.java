package com.company;

import java.io.*;
import java.util.ArrayList;

public class Meeting {

    private String nrIndex;
    private String data;
    private String exist;


    private ArrayList<Student> studentArray;

    public Meeting(ArrayList<Student> studentArrayList) { //widoczna arraylist
        this.studentArray = studentArrayList;
    }

    private ArrayList<Meeting> meetingsList = new ArrayList<>();

    public ArrayList<Meeting> getMeetingsList() {
        return meetingsList;
    }

    public Meeting() {
    }

    public Meeting(String nrIndex, String data, String exist) {
        this.nrIndex = nrIndex;
        this.data = data;
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "nrIndex='" + nrIndex + '\'' +
                ", data='" + data + '\'' +
                ", exist=" + exist +
                '}';
    }

    public void addMeeting(String nrIndex, String data, String exist) {

        if (meetingsList.size() < 15) {
            Meeting meeting = new Meeting(nrIndex, data, exist);

            int size = studentArray.size();

            for (int i = 0; i < size; i++) {

                if (studentArray.get(i).getNrIndex() == nrIndex) {
                    meetingsList.add(meeting);
                }
            }
        } else throw new IllegalArgumentException("za duzo spotkan");
    }

    public int numberExist(String nrIndex) {
        int number = 0;

        int size = meetingsList.size();
        if (meetingsList.isEmpty()) {
            System.out.println("Brak zarejestrowanych obecnosci");
        } else {
            for (int i = 0; i < size; i++) {
                if (meetingsList.get(i).nrIndex == nrIndex) {
                    if (meetingsList.get(i).exist == "true") number += 1;
                }
            }
        }
        return number;
    }

    public void saveToFile() throws FileNotFoundException {

        int size = meetingsList.size();
        String toSave = "";
        for (int i = 0; i < size; i++) {
            toSave += meetingsList.get(i).getNrIndex() + ","
                    + meetingsList.get(i).getData() + ","
                    + meetingsList.get(i).getExist() + ";";
        }
        PrintWriter writer = new PrintWriter("Meeting.txt");
        writer.println(toSave);
        writer.close();
    }

    public String readFromFile() throws FileNotFoundException {

        try (FileReader reader = new FileReader("Meeting.txt");
             BufferedReader br = new BufferedReader(reader)) {

            String line = "";

            while ((line = br.readLine()) != null) {

                String[] tab = line.split(";");
                int i = 0;
                String[] tablica = new String[100];

                for (String b : tab) {
                    String[] bb = b.split(",");
                    for (String a : bb) {
                        tablica[i] = a;
                        if (i == meetingsList.size()) {
                            i = 0;
                        }
                        i++;
                    }
                }
                for (int j = 0; j < i - 1; j += 3) {
                    System.out.println(tablica[j] + ", " + tablica[j + 1] + "," + tablica[j + 2]);

                    Meeting meeting = new Meeting();
                    meeting.setNrIndex(tablica[j]);
                    meeting.setData(tablica[j + 1]);
                    meeting.setExist(tablica[j + 2]);
                    meetingsList.add(meeting);
                }

            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println(meetingsList.toString());
        return meetingsList.toString();
    }

    public String getNrIndex() {
        return nrIndex;
    }

    public void setNrIndex(String nrIndex) {
        this.nrIndex = nrIndex;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExist() {
        return exist;
    }

    private void setExist(String exist) {
        this.exist = exist;
    }
}

package com.company;

import org.junit.Test;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class MeetingTest {

    @Test
    public void shouldBeAbleToCreatMeeting() {
        Meeting meeting = new Meeting("8382", "10.10.2019", "true");
        assertNotNull(meeting);
    }

    @Test
    public void shouldBeAbleToAddMeeting() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");

        Meeting meeting = new Meeting(student.getStudentArrayList());

        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8585", "13.10.2019", "false");
        int lastIndex = meeting.getMeetingsList().size() - 1;
        assertEquals("false", meeting.getMeetingsList().get(lastIndex).getExist());

    }

    @Test
    public void shouldBeNotAbleToAddMeetingWhetWrongId() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");

        Meeting meeting = new Meeting(student.getStudentArrayList());

        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8584", "13.10.2019", "false");
        int lastIndex = meeting.getMeetingsList().size() - 1;
        assertEquals("true", meeting.getMeetingsList().get(lastIndex).getExist());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldBeNotAbleToAddMeetingWhenToManyMeeting() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");

        Meeting meeting = new Meeting(student.getStudentArrayList());

        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8585", "12.10.2019", "false");
        int lastIndex = meeting.getMeetingsList().size() - 1;
        assertEquals("true", meeting.getMeetingsList().get(lastIndex).getExist());
    }

    @Test
    public void shouldReturnExistStudent() {

        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Meeting meeting = new Meeting(student.getStudentArrayList());

        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        assertEquals(3, meeting.numberExist("8585"));
    }

    @Test
    public void shouldSaveToFile() throws FileNotFoundException {
        Student student = new Student();
        student.addStudent("Ola", "Radzik", "8587");
        student.addStudent("Ala", "Maj", "8586");
        student.addStudent("Ala", "Maj", "8585");
        Meeting meeting = new Meeting(student.getStudentArrayList());

        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.addMeeting("8585", "12.10.2019", "true");
        meeting.addMeeting("8585", "10.10.2019", "true");
        meeting.addMeeting("8585", "11.10.2019", "false");
        meeting.saveToFile();
    }

    @Test
    public void shouldReadFromFile() throws FileNotFoundException {

        Meeting meeting = new Meeting();
        meeting.readFromFile();
        String result = "[Meeting{nrIndex='8585', data='10.10.2019', exist=true}, Meeting{nrIndex='8585', data='11.10.2019', exist=false}, Meeting{nrIndex='8585', data='12.10.2019', exist=true}, Meeting{nrIndex='8585', data='10.10.2019', exist=true}, Meeting{nrIndex='8585', data='11.10.2019', exist=false}, Meeting{nrIndex='8585', data='true', exist=8585}, Meeting{nrIndex='11.10.2019', data='false', exist=10.10.2019}]";
        assertEquals(meeting.readFromFile(), result);
    }
}
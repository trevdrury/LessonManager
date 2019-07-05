package com.trevordrury.android.lessonmanager.model;

import java.util.UUID;

public class Student {

    private UUID ID;
    private String studentName;
    private String studioName;
    private String studentInstrument;
    private String lessonDay;
    private String lessonTime;
    private String parentName;
    private String phone;
    private String email;

    public Student() {
        ID = UUID.randomUUID();
    }

    public Student(UUID id) {
        ID = id;
    }

    public UUID getID() { return ID; }

    public String getStudentName() { return studentName; }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public String getStudioName() { return studioName; }

    public void setStudioName(String name) {
        this.studioName = name;
    }

    public String getStudentInstrument() { return studentInstrument; }

    public void setStudentInstrument(String instrument) {
        this.studentInstrument = instrument;
    }

    public String getLessonDay() { return lessonDay; }

    public void setLessonDay(String day) {
        this.lessonDay = day;
    }

    public String getLessonTime() { return lessonTime; }

    public void setLessonTime(String time) {
        this.lessonTime = time;
    }

    public String getParentName() { return parentName; }

    public void setParentName(String name) {
        this.parentName = name;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

}

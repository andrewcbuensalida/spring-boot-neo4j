package com.anhonestobserver.springbootneo4j.objects;

import com.anhonestobserver.springbootneo4j.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private String identifier;
    private String title;
    private String teacher;
    private List<Lesson> lessons = new ArrayList<>();

    public CourseDTO(String identifier, String title, String teacher) {
        this.identifier = identifier;
        this.teacher = teacher;
        this.title = title;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTitle() {
        return title;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}

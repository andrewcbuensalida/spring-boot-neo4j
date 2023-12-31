package com.anhonestobserver.springbootneo4j.objects;

import com.anhonestobserver.springbootneo4j.models.Course;

public class CourseEnrollmentDTO {
    private String username;
    private String name;
    private Course course;

    public CourseEnrollmentDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

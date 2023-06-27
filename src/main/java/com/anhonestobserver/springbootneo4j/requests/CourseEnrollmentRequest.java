package com.anhonestobserver.springbootneo4j.requests;

public class CourseEnrollmentRequest {
    private String courseIdentifier;

    public CourseEnrollmentRequest() {
    }

    public String getCourseIdentifier() {
        return courseIdentifier;
    }

    public void setCourseIdentifier(String courseIdentifier) {
        this.courseIdentifier = courseIdentifier;
    }
}

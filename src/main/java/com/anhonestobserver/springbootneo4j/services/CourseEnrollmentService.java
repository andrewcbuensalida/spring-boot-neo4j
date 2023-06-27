// needed to create this file because it would be awkward to have a userRepository in the CourseService
package com.anhonestobserver.springbootneo4j.services;

import com.anhonestobserver.springbootneo4j.models.Course;
import com.anhonestobserver.springbootneo4j.queryresults.CourseEnrollmentQueryResult;
import com.anhonestobserver.springbootneo4j.repositories.CourseRepository;
import com.anhonestobserver.springbootneo4j.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEnrollmentService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseEnrollmentService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Boolean getEnrollmentStatus(String username,String identifier){
        return userRepository.findEnrollmentStatus(username,identifier);
    }
    public CourseEnrollmentQueryResult enrollIn(String username, String identifier){
        return userRepository.createEnrollmentRelationship(username,identifier);
    }

    public List<Course> getAllEnrolledCoursesByUsername(String username){
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }
}

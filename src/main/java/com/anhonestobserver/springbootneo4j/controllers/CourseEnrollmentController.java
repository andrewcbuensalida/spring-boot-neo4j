package com.anhonestobserver.springbootneo4j.controllers;

import com.anhonestobserver.springbootneo4j.models.Course;
import com.anhonestobserver.springbootneo4j.models.Lesson;
import com.anhonestobserver.springbootneo4j.objects.CourseDTO;
import com.anhonestobserver.springbootneo4j.objects.CourseEnrollmentDTO;
import com.anhonestobserver.springbootneo4j.queryresults.CourseEnrollmentQueryResult;
import com.anhonestobserver.springbootneo4j.requests.CourseEnrollmentRequest;
import com.anhonestobserver.springbootneo4j.services.CourseEnrollmentService;
import com.anhonestobserver.springbootneo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enrollments")
public class CourseEnrollmentController {
    private final CourseEnrollmentService courseEnrollmentService;
    private final LessonService lessonService;

    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService, LessonService lessonService) {
        this.courseEnrollmentService = courseEnrollmentService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> enrollments(Principal principal){
        List<Course> courses = courseEnrollmentService.getAllEnrolledCoursesByUsername(principal.getName());
        List<CourseDTO> responseCourses = courses.stream().map(
                course -> {
                    CourseDTO responseCourse = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
                    responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
                    return responseCourse;
                }
        ).collect(Collectors.toList());
        return new ResponseEntity<>(responseCourses,HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CourseEnrollmentDTO> enrollIn(@RequestBody CourseEnrollmentRequest request, Principal principal){ // Principal is how you get the username of the authenticated user
        CourseEnrollmentQueryResult enrollment = courseEnrollmentService.enrollIn(principal.getName(), request.getCourseIdentifier());
        CourseEnrollmentDTO responseEnrollment = new CourseEnrollmentDTO();
        responseEnrollment.setName(enrollment.getUser().getName());
        responseEnrollment.setUsername(enrollment.getUser().getUsername());
        responseEnrollment.setCourse(enrollment.getCourse());
        // responseEnrollment is the body
        return new ResponseEntity<>(responseEnrollment, HttpStatus.OK);
    }
}

// Controllers are responsible for taking and giving data
package com.anhonestobserver.springbootneo4j.controllers;

import com.anhonestobserver.springbootneo4j.models.Course;
import com.anhonestobserver.springbootneo4j.objects.CourseDTO;
import com.anhonestobserver.springbootneo4j.services.CourseService;
import com.anhonestobserver.springbootneo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    private final LessonService lessonService;

    public CourseController(CourseService courseService,LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> courseIndex(){
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> responseCourses = courses.stream().map((course -> {
            CourseDTO responseCourse = new CourseDTO(course.getIdentifier(),course.getTitle(),course.getTeacher());
            responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
            return responseCourse;
        })).collect(Collectors.toList());
        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<CourseDTO> courseDetails(@PathVariable String identifier){
        Course course = courseService.getCourseByIdentifier(identifier);

        CourseDTO responseCourse = new CourseDTO(course.getIdentifier(),course.getTitle(),course.getTeacher()); // identifier and course.getIdentifier() seem to be the same

        responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));

        return new ResponseEntity<>(responseCourse,HttpStatus.OK);
    }
}

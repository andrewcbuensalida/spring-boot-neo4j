package com.anhonestobserver.springbootneo4j.controllers;

import com.anhonestobserver.springbootneo4j.models.Course;
import com.anhonestobserver.springbootneo4j.models.User;
import com.anhonestobserver.springbootneo4j.objects.UserDTO;
import com.anhonestobserver.springbootneo4j.services.CourseEnrollmentService;
import com.anhonestobserver.springbootneo4j.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UsersCoursesController {
    private final CourseEnrollmentService courseEnrollmentService;
    private final UserService userService;

    public UsersCoursesController(CourseEnrollmentService courseEnrollmentService, UserService userService) {
        this.courseEnrollmentService = courseEnrollmentService;
        this.userService = userService;
    }

    // To get all users with their courses
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> users = userService.getUsers();
        List<UserDTO> responseUsers = users.stream().map(user->{
            // get courses of the user
            List<Course> courses = courseEnrollmentService.getAllEnrolledCoursesByUsername(user.getUsername());
            // transform the list of courses to a list of just identifiers
            List<String> coursesIdentifiers = courses.stream().map(course->{
                return course.getIdentifier();
            }).collect(Collectors.toList());
            UserDTO responseUser = new UserDTO(user.getName(), user.getUsername(), user.getRoles());
            responseUser.setCoursesIdentifiers(coursesIdentifiers);
            return responseUser;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(responseUsers,HttpStatus.OK);
    }
}

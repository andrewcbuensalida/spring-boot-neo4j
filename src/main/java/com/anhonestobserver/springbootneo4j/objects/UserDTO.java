package com.anhonestobserver.springbootneo4j.objects;

import java.util.List;

public class UserDTO {
    private String name;
    private String username;
    private String roles;
    private List<String> coursesIdentifiers;

    public UserDTO(String name, String username, String roles) {
        this.name = name;
        this.username = username;
        this.roles = roles;
    }

    public List<String> getCoursesIdentifiers() {
        return coursesIdentifiers;
    }

    public void setCoursesIdentifiers(List<String> coursesIdentifiers) {
        this.coursesIdentifiers = coursesIdentifiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

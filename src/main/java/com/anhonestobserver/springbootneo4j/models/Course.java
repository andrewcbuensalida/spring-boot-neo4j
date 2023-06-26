package com.anhonestobserver.springbootneo4j.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Course {
    @Id @GeneratedValue
    private Long id;
    private String identifier;
    private String title;
    private String teacher;

    // When getting a course, this adds the lessons to it. Used with getLessons method below. Every time you query the courses, you get the relationship back. Instead of this, do a custom cypher query in the LessonRepository
//    @Relationship( type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
//    private List<Lesson> lessons = new ArrayList<>();

    public Course() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    // asdf or get doesn't work, but works if it's getASDF or getLessons. Instead of this, do a custom cypher query in the LessonRepository.
//    public List<Lesson> getLessons() {
//        return lessons;
//    }
}

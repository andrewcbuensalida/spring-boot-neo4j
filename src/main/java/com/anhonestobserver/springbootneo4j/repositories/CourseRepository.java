package com.anhonestobserver.springbootneo4j.repositories;

import com.anhonestobserver.springbootneo4j.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface CourseRepository extends Neo4jRepository<Course,Long> {
    Optional<Course> findCourseByIdentifier(String identifier);
}

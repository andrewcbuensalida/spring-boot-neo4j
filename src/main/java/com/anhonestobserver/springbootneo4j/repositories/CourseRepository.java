package com.anhonestobserver.springbootneo4j.repositories;

import com.anhonestobserver.springbootneo4j.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends Neo4jRepository<Course,Long> {
    Optional<Course> findCourseByIdentifier(String identifier);

    @Query("MATCH (user:User)-[:ENROLLED_IN]->(course:Course) WHERE user.username = $username RETURN course")
    List<Course> findAllEnrolledCoursesByUsername(String username);
}

package com.anhonestobserver.springbootneo4j.repositories;

import com.anhonestobserver.springbootneo4j.models.User;
import com.anhonestobserver.springbootneo4j.queryresults.CourseEnrollmentQueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    @Query("MATCH (u:User) RETURN DISTINCT u")
    List<User> getUsers();

    @Query("MATCH (user:User), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " + "RETURN EXISTS((user)-[:ENROLLED_IN]->(course))")
//    @Query("MATCH (:User{username:$username})-[:ENROLLED_IN]->(course:Course{identifier:$identifier}) RETURN COUNT(course) > 0")
    Boolean findEnrollmentStatus(String username, String identifier);

    @Query("MATCH (user:User), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " + "CREATE (user)-[:ENROLLED_IN]->(course) RETURN user, course") // since it returns something complex, we have to create CourseEnrollmentQueryResult
    CourseEnrollmentQueryResult createEnrollmentRelationship(String username, String identifier);
}

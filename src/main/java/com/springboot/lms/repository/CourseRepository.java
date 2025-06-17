package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer>{
    // @Query("select c from Course c where c.author.user.username=?1")
    @Query("select c from Course c join c.author a join a.user u where u.username=?1")
    List<Course> getCoursesByAuthor(String username);

    @Query("select c from Course c join c.author a where c.id = ?1")
    Optional<Course> findCourseWithAuthorById(int id);
}
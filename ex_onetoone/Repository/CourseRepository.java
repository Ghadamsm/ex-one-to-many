package com.example.ex_onetoone.Repository;

import com.example.ex_onetoone.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course , Integer> {


    Course findCourseById(Integer id);
}

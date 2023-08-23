package com.imagineLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagineLearning.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}


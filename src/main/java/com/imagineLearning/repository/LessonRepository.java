package com.imagineLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagineLearning.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{

}


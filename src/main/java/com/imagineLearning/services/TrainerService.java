package com.imagineLearning.services;

import java.util.List;

import com.imagineLearning.entity.Course;
import com.imagineLearning.entity.Lesson;

public interface TrainerService {
	public Course addCourse(Course course);
	
	public String saveCourse(Course course);
	
	public String addLesson(Lesson lesson);
	
	public Course getCourse(int courseId);
	
	public List<Course> courseList();
}


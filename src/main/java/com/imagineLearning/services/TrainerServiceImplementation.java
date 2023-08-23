package com.imagineLearning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagineLearning.entity.Course;
import com.imagineLearning.entity.Lesson;
import com.imagineLearning.repository.CourseRepository;
import com.imagineLearning.repository.LessonRepository;

@Service
public class TrainerServiceImplementation implements TrainerService{

	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	LessonRepository lessonRepo;
	
	@Override
	public Course addCourse(Course course) {
		return courseRepo.save(course);
	}
	@Override
	public String addLesson(Lesson lesson) {
		lessonRepo.save(lesson);
		return "lesson added successfully!";
	}
	@Override
	public Course getCourse(int courseId) {
		return courseRepo.findById(courseId).get();
	}
	@Override
	public List<Course> courseList() {
		return courseRepo.findAll();
	}
	@Override
	public String saveCourse(Course course) {
		courseRepo.save(course);
		return "Course saved successfully!";
	}
	
}


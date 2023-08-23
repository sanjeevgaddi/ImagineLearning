package com.imagineLearning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagineLearning.entity.Lesson;
import com.imagineLearning.repository.LessonRepository;
import com.imagineLearning.repository.UserRepository;

@Service
public class StudentServiceImplementation implements StudentService{
	@Autowired
	UserRepository userRepo;
	@Autowired
	LessonRepository lessonRepo;
	@Override
	public Lesson getLesson(int lessonId) {
		
		return lessonRepo.findById(lessonId).get();
		
	}
	
	

}


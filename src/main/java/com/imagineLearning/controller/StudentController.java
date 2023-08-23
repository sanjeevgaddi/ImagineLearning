package com.imagineLearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imagineLearning.entity.Comments;
import com.imagineLearning.entity.Course;
import com.imagineLearning.entity.Lesson;
import com.imagineLearning.entity.Users;
import com.imagineLearning.services.CommentService;
import com.imagineLearning.services.StudentService;
import com.imagineLearning.services.TrainerService;
import com.imagineLearning.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	@Autowired
	StudentService sService;
	
	@Autowired
	UserService uService;
	
	@Autowired
	TrainerService tService;
	
	@Autowired
	CommentService cService;
	
	@GetMapping("/viewLesson")
	public String viewLesson(@RequestParam("lessonId")int lessonId,
							Model model,HttpSession session) {
	//	Users user = (Users) session.getAttribute("loggedInUser");
		
		Lesson lesson = sService.getLesson(lessonId);
		// Extract the YouTube video id from the URL
	    String youtubeUrl = lesson.getLink();
	    
	    String videoId = youtubeUrl.substring(youtubeUrl.indexOf("=") + 1);
	    lesson.setLink(videoId);
		
		
		model.addAttribute("lesson",lesson);
		List<Comments> commentsList=cService.commentList();
		model.addAttribute("comments",commentsList);
		
		return "myLesson";
	}
	
	@PostMapping("/addComment")
	public String comments(@RequestParam("comment")String comment
						,Model model) {
		Comments c=new Comments();
		c.setComment(comment);
		cService.addComment(c);
		
		List<Comments> commentsList=cService.commentList();
		model.addAttribute("comments",commentsList);
		
		return "myLesson";
	}

	@GetMapping("/purchase")
	public String showCourses(Model model,HttpSession session) {
		Users user = (Users) session.getAttribute("loggedInUser");

		List<Course> courseList=tService.courseList();
		model.addAttribute("courseList",courseList);
		model.addAttribute("loggedInUser",user);
		return "purchase";
	}
	
	
	@GetMapping("/fetchCourses")
	public String fetchCourses(Model model, HttpSession session) {
		
		Users loggedUser=(Users) session.getAttribute("loggedInUser");
		
		String email=loggedUser.getEmail();
				
		Users user=uService.getUser(email);
			
		List<Course> courseList=user.getCourses();
		model.addAttribute("courseList",courseList);
		
		
		return "myCourses";
	}
	
	
}


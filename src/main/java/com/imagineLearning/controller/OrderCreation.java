package com.imagineLearning.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imagineLearning.entity.Course;
import com.imagineLearning.entity.Users;
import com.imagineLearning.services.TrainerService;
import com.imagineLearning.services.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
public class OrderCreation {
	
	@Autowired
	UserService uService;
	
	@Autowired
	TrainerService tService;
	
	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestParam("amount") int amount,
							@RequestParam("email") String email,
							@RequestParam("courseId") int courseId) {
//		System.out.println(amount+email+courseId);
		
		Order order=null;
		try {
			RazorpayClient razorpay=new RazorpayClient("rzp_test_ANiFbfIjOIx3EF", "jgHc0g5bAebbeilwWX1uXP45");
			
			JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", "order_rcptid_11");

			  order = razorpay.orders.create(orderRequest);
			  
			  
			  attachCourse(email, courseId);
			  
		
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
	}
	public void attachCourse(String email, int courseId) {
		
		
		Course course=tService.getCourse(courseId);
		Users user=uService.getUser(email);
		
		user.getCourses().add(course);
		course.getUserList().add(user);
	
		uService.updateUser(user);
		tService.saveCourse(course);
	}
}


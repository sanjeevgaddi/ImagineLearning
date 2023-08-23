package com.imagineLearning.services;

import java.util.List;

import com.imagineLearning.entity.Comments;

public interface CommentService {
	List<Comments> commentList();
	String addComment(Comments comment);
}


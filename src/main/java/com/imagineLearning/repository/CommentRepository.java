package com.imagineLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagineLearning.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments,Integer>{

}


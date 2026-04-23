package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FeedBackEntity;

@Repository

public interface FeedBackRepo extends JpaRepository<FeedBackEntity,Integer>{

	Object findByFeedback(String msg);

}

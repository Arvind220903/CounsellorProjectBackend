package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CounsellorEntity;

@Repository

public interface CounsellorRepo extends JpaRepository<CounsellorEntity,Integer>{

	CounsellorEntity findByCounselloremail(String username);

}

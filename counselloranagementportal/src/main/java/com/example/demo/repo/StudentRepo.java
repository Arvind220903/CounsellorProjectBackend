package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentEntity;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity,Integer>{

	StudentEntity findByStudentemail(String studentemail);

	List<StudentEntity> findByCounsellorid(Integer id);

}

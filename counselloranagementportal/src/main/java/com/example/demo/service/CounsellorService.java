package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CounsellorEntity;
import com.example.demo.entity.StudentEntity;

@Service
public interface CounsellorService {
		public String addStudent(StudentEntity student,String email);
		
		public String register(CounsellorEntity counsellor);
		public String changeStatus(int studentId, String newStatus);
		public Map<String,Integer> dashBoard(String email);
		public List<StudentEntity> getStudents(String email);
		public List<String> courses();
		public String feedback(String msg);
		

}

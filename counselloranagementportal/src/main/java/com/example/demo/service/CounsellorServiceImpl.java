package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CounsellorEntity;
import com.example.demo.entity.FeedBackEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repo.CounsellorRepo;
import com.example.demo.repo.FeedBackRepo;
import com.example.demo.repo.StudentRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	@Autowired
	private BCryptPasswordEncoder bc;
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private CounsellorRepo cr;

	@Override
	public String addStudent(StudentEntity student, String email) {
		CounsellorEntity counsellor = cr.findByCounselloremail(email);
		student.setCounsellorid(counsellor.getCounsellorid());
		if (studentRepo.save(student) == null)
			return "Fail";
		return "Success";
	}

	@Override
	public String register(CounsellorEntity counsellor) {
		counsellor.setPassword(bc.encode(counsellor.getPassword()));
		if (cr.findByCounselloremail(counsellor.getCounselloremail()) != null)
			return "Fail";
		if (cr.save(counsellor) == null)
			return "failed";
		return "Successfully Register";
	}

	@Override
	public String changeStatus(int studentId, String newStatus) {
		return studentRepo.findById(studentId).map(student -> {
			student.setStatus(newStatus);
			studentRepo.save(student);
			return "Success";
		}).orElse("Fail");
	}

	@Override
	public Map<String, Integer> dashBoard(String email) {
		Map<String, Integer> dash = new HashMap<>();
		CounsellorEntity counsellor = cr.findByCounselloremail(email);
		List<StudentEntity> l1 = getStudents(email);
		for (StudentEntity student : l1) {
			dash.put(student.getStatus(), dash.getOrDefault(student.getStatus(), 0) + 1);
		}
		return dash;
	}

	@Override
	public List<StudentEntity> getStudents(String email) {
		CounsellorEntity counsellor = cr.findByCounselloremail(email);
		List<StudentEntity> l1 = studentRepo.findByCounsellorid(counsellor.getCounsellorid());
		return l1;
	}

	@Override
	public List<String> courses() {
		List<String> l = new ArrayList<>();
		l.add("Java");
		l.add("Python");
		l.add("Devops");
		l.add("AIML");
		l.add("Mern");
		return l;
	}
	@Autowired 
	private FeedBackRepo fr;
	public String feedback(String msg) {
		if(fr.findByFeedback(msg)!=null)return "ok";
		FeedBackEntity fb=new FeedBackEntity();
		fb.setFeedback(msg);
		fr.save(fb);
		return "ok";
	}

}

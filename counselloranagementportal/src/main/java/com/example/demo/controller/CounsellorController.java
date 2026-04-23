package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CounsellorEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.jwt.JwtService;
import com.example.demo.service.CounsellorService;

@RestController
public class CounsellorController {

	@Autowired
	private CounsellorService counsellorservice;
	@Autowired
	private AuthenticationManager auth;
	@Autowired
	private JwtService jwt;

	// ─── POST /login ──────────────────────────────────────────────────────────
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody CounsellorEntity counsellor) {
		try {
			Authentication authentication = auth.authenticate(
					new UsernamePasswordAuthenticationToken(
							counsellor.getCounselloremail(), counsellor.getPassword()));
			if (authentication.isAuthenticated())
				return ResponseEntity.ok(jwt.generateKey(counsellor.getCounselloremail()));
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	// ─── POST /register ───────────────────────────────────────────────────────
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody CounsellorEntity counsellor) {
		String result = counsellorservice.register(counsellor);
		if (result.equals("Fail") || result.equals("failed"))
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	// ─── POST /addstudent ────────────────────────────────────────────────────
	@PostMapping("/addstudent")
	public ResponseEntity<String> addstudent(@RequestBody StudentEntity student,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7); // strip "Bearer "
		String email = jwt.extractUsername(token);
		String result = counsellorservice.addStudent(student, email);
		if (result.equals("Fail"))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	// ─── GET /getstudent/{id} ─────────────────────────────────────────────────
	@GetMapping("/getstudent")
	public ResponseEntity<List<StudentEntity>> get(@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		String email=jwt.extractUsername(token);
		List<StudentEntity> students = counsellorservice.getStudents(email);
		if (students == null || students.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(students);
	}

	// ─── GET /courses ─────────────────────────────────────────────────────────
	@GetMapping("/courses")
	public ResponseEntity<List<String>> courses() {
		return ResponseEntity.ok(counsellorservice.courses());
	}

	// ─── GET /dashboard ───────────────────────────────────────────────────────
	@GetMapping("/dashboard")
	public ResponseEntity<Map<String, Integer>> dash(
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7); // strip "Bearer "
		String email = jwt.extractUsername(token);
		Map<String, Integer> result = counsellorservice.dashBoard(email);
		if (result == null || result.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(result);
	}
	// ─── PUT /changestatus/{studentId} ───────────────────────────────────────
	@PutMapping("/changestatus/{studentId}")
	public ResponseEntity<String> changeStatus(
			@PathVariable int studentId,
			@RequestBody String newStatus) {
		String result = counsellorservice.changeStatus(studentId, newStatus);
		if (result.equals("Fail"))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		return ResponseEntity.ok(result);
	}

	// ─── POST /feedback ───────────────────────────────────────────────────────
	@PostMapping("/feedback")
	public ResponseEntity<String> feedback(@RequestBody String message) {
		counsellorservice.feedback(message);
		return ResponseEntity.ok("Your feedback has been recorded. Thank you!");
	}
}

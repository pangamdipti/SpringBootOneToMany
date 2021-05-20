package com.poc8.springbootpoc8.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poc8.springbootpoc8.model.Student;

public interface StudentService {

	Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

	Student saveStudentAndUpdate(Student student);

	void deleteStudentById(Long id);

	Student findStudentById(Long id);

	List<Student> getAllStudents(String keyword);
	
}

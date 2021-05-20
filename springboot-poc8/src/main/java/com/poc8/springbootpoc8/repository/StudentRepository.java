package com.poc8.springbootpoc8.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc8.springbootpoc8.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query(value = "SELECT s from Student s WHERE CONCAT(s.id,'') LIKE %?1%")
	public List<Student> search(String studentId);

	@Query(value = "SELECT s from Student s WHERE CONCAT(s.id,'') LIKE %?1%")
	public Student findStudentById(Long id);

}

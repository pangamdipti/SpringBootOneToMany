package com.poc8.springbootpoc8.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poc8.springbootpoc8.model.Project;
import com.poc8.springbootpoc8.model.Student;
import com.poc8.springbootpoc8.service.ProjectService;
import com.poc8.springbootpoc8.service.StudentService;

@RestController
@Controller
public class ServiceController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private ProjectService projectService;

	// display list of students
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {

		int pageSize = 10;

		Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Student> listStudents = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("students", listStudents);
		model.addAttribute("listStudents", listStudents);
		model.addAttribute("projectList",projectService.findAllProject());
		return "index";
	}

	// new student form
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		
		return "new_student";
	}

	// saving new student
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student,
			@RequestParam("image") MultipartFile multipartFile)throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		student.setPhotos(fileName);
		
		Student savedStudent=studentService.saveStudentAndUpdate(student);
		
		String uploadDir = "student-photos/"+savedStudent.getId();
		
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		return "redirect:/";
	}

	
	//delete student
	@DeleteMapping("/deleteStudent/{id}") //for testing
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable(value = "id") long id) {
		studentService.deleteStudentById(id);
		return "redirect:/";
	}
	
	
	
	//update student form
	@GetMapping("/updateStudentForm/{id}")
	public String updateStudentForm(@PathVariable(value = "id") Long studentId, Model model) {
		Student student = studentService.findStudentById(studentId);
		model.addAttribute("student", student);
		return "update_student";
	}
	
	//update student
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute("student") Student student) {
		
		studentService.saveStudentAndUpdate(student);
		return "redirect:/";
	}
	
	
	//add project form
	@GetMapping("/addStudentProject/{id}")
	public String addStudentProjectForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("student", studentService.findStudentById(id));
		model.addAttribute("projectList",projectService.findAllProject());

		model.addAttribute("project", new Project());
		
		return "addStudentProject";
	}
	
	//save project form
	@PostMapping("/student/{id}/saveProject")
	public String saveStudentProject(@PathVariable(value ="id") Long id,
			@ModelAttribute("project") Project project, Model model) {
		
		Student student = studentService.findStudentById(id);
		
		project.setStudent(student);
		projectService.saveProject(project);
		
		model.addAttribute("student", student);
		model.addAttribute("projectList",projectService.findAllProject());
		
		return "addStudentProject";

	}
	
	
	//delete project
	@DeleteMapping("/student/{studentId}/deleteProject/{id}") //for testing
	@GetMapping("/student/{studentId}/deleteProject/{id}")
	public String deleteProject(@PathVariable(value = "studentId") Long studentId,
			@PathVariable(value = "id") Long id, Model model) {
		projectService.deleteProjectById(id);
		model.addAttribute("student", studentService.findStudentById(studentId));
		model.addAttribute("projectList",projectService.findAllProject());
		model.addAttribute("project", new Project()); 
		
		return "addStudentProject";
		
	}


	//search student
	@GetMapping("/student/search")
	public String searchStudent(Model model, @Param("keyword") String keyvalue) {
		System.out.println(keyvalue);
		List<Student> students = studentService.getAllStudents(keyvalue);
		for(Student student : students){
			System.out.println(student);
			
		}
		model.addAttribute("listStudents", studentService.getAllStudents(null));
		model.addAttribute("projectList",projectService.findAllProject());
		model.addAttribute("students",students);
		model.addAttribute("keyvalue", keyvalue);
		return "index";
	}

}
 
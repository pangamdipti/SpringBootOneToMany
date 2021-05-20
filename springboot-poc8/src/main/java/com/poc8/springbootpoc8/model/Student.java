package com.poc8.springbootpoc8.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "contact")
	private String contact;

	@Column(name = "email")
	private String email;

	@Column(nullable = true, length = 64)
	private String photos;
	
	@Transient
	public String getPhotosImagePath() {
		if(photos == null)
			return null;
		return "/student-photos/"+id+"/"+photos;
	}
	

	/*
	 * @ManyToOne(cascade = CascadeType.MERGE)
	 * 
	 * @JoinTable(name = "student_project", joinColumns = { @JoinColumn(name = "id")
	 * }, inverseJoinColumns = {@JoinColumn(name = "projectId") }) Set<Project>
	 * project = new HashSet<>();
	 */

	/*
	 * public boolean hasProject(Project project) { for (Project studentProject :
	 * getProject()) { if (studentProject.getProjectId() == project.getProjectId())
	 * { return true; } } return false; }
	 */

}

package com.poc8.springbootpoc8.service;

import java.util.List;

import com.poc8.springbootpoc8.model.Project;

public interface ProjectService {

	List<Project> findAllProject();

	void saveProject(Project project);

	void deleteProjectById(Long id);

}

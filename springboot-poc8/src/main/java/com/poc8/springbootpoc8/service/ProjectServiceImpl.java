package com.poc8.springbootpoc8.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc8.springbootpoc8.model.Project;
import com.poc8.springbootpoc8.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired 
	ProjectRepository projectRepository;

	@Override
	public List<Project> findAllProject() {
		return projectRepository.findAll();
	}

	@Override
	public void saveProject(Project project) {
		projectRepository.save(project);
		
	}

	@Override
	public void deleteProjectById(Long id) {
		projectRepository.deleteById(id);
		
	}

}

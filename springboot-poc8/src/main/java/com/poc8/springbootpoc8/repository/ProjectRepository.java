package com.poc8.springbootpoc8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc8.springbootpoc8.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{


}

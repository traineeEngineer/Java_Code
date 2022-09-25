package com.Department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Department.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

}

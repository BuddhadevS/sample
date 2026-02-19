package com.zed.Interview.repository;

import com.zed.Interview.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {


    List<Student> findByName(String name);
}

package com.zed.Interview.service;

import com.zed.Interview.model.Student;
import com.zed.Interview.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public Student saveStudent(Student student) {
       return studentRepo.save(student);

    }

    public Student findById(Integer id) {
        return  studentRepo.findById(id).orElseThrow(()-> new RuntimeException("Student id not found "+id));
    }

    public List<Student> findAll(Student student) {
        return studentRepo.findAll();
    }

    public List<Student> findByName(String name) {
        return  studentRepo.findByName(name);


    }
}

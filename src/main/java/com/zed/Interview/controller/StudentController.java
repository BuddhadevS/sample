package com.zed.Interview.controller;

import com.zed.Interview.model.Student;
import com.zed.Interview.repository.StudentRepo;
import com.zed.Interview.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class StudentController {

   private final StudentRepo studentRepo;
   private final StudentService studentService;
   public StudentController(StudentRepo studentRepo, StudentService studentService) {
       this.studentRepo = studentRepo;
       this.studentService = studentService;


   }
   @PostMapping("/add")
   public Student saveData(@RequestBody Student student) {
       return studentService.saveStudent(student);
   }

   @GetMapping("/{id}")
   public Student getData(@PathVariable Integer id){
       return studentService.findById(id);
   }

   @GetMapping("/all")
   public List<Student> findAllData(Student student){
       return studentService.findAll(student);
   }

   @GetMapping("/findbyname/{name}")
   public List<Student> findByName(@PathVariable String name){
       return studentService.findByName(name);
   }



}

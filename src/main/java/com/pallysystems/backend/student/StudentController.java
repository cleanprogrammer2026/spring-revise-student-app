package com.pallysystems.backend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(path = "")
    public List<Student> students() {
        return studentService.getStudents();
    }

    @PostMapping(path = "")
    public void registerStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void editStudent(@PathVariable("studentId") Long studentId,
                            @RequestBody Student student) {
        studentService.updateStudent(studentId, student);
    }
}

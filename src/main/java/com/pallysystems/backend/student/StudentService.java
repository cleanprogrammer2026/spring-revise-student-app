package com.pallysystems.backend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Student email already taken.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);

        if (!studentExists) {
            throw new IllegalStateException("Student not found!");
        }
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long studentId, Student student) {
        if(!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("Student not found!");
        }
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isPresent()) {
            Student studentRecord = optionalStudent.get();
            studentRecord.setName(student.getName());
            studentRecord.setEmail(student.getEmail());
            studentRecord.setDob(student.getDob());
            studentRepository.save(studentRecord);
        }
    }
}

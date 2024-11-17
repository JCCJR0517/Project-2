package edu.hccs.myspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/name/{name}")
    public List<Student> searchByName(@PathVariable String name) {
        return studentRepository.findByFirstNameContainingIgnoreCase(name);
    }

    @GetMapping("/student")
    public List<Student> searchByGpaAndGender(@RequestParam double minGpa,
                                              @RequestParam double maxGpa,
                                              @RequestParam String gender) {
        return studentRepository.findByGpaBetweenAndGender(minGpa, maxGpa, gender);
    }

    @GetMapping("/gpa")
    public double calculateAverageGpa(@RequestParam String gender) {
        List<Student> students = studentRepository.findByGender(gender);
        return students.stream().mapToDouble(Student::getGpa).average().orElse(0.0);
    }
}

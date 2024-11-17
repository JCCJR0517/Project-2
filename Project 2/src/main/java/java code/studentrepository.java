package edu.hccs.myspring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
    List<Student> findByFirstNameContainingIgnoreCase(String name);
    
    List<Student> findByGpaBetweenAndGender(double minGpa, double maxGpa, String gender);
    
    List<Student> findByGender(String gender);
}

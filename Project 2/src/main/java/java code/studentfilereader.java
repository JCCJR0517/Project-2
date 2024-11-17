package edu.hccs.myspring;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class StudentFileReader {
    
    private static final String FILE_PATH = "path/to/student.txt"; // Change this to the actual path

    public List<Student> readAndParseStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        
        while ((line = reader.readLine()) != null) {
            try {
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Incorrect number of fields");
                }

                String firstName = parts[1].trim();
                double gpa = Double.parseDouble(parts[2].trim());
                String email = parts[3].trim();
                String gender = parts[4].trim();
                
                if (!isValidEmail(email)) {
                    throw new IllegalArgumentException("Invalid email format");
                }

                Student student = new Student(firstName, gpa, email, gender);
                students.add(student);

            } catch (Exception e) {
                System.err.println("Error parsing student data: " + e.getMessage());
            }
        }
        reader.close();
        return students;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}

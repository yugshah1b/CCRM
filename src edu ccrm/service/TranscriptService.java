package edu.ccrm.service;

import java.util.List;
import java.util.function.Function;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Transcript;
import edu.ccrm.enums.Grade;
import edu.ccrm.store.DataStore;

/**
 * Service for managing transcripts and grade calculations.
 */
public class TranscriptService {
    private final DataStore store = DataStore.getInstance();
    
    /**
     * Generate a complete transcript for a student.
     */
    public Transcript generateTranscript(String studentId, Function<String, Integer> creditsLookup) {
        Student student = store.students().get(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found: " + studentId);
        }
        
        List<Enrollment> enrollments = store.enrollments().stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .toList();
        
        return new Transcript(student, enrollments);
    }
    
    /**
     * Record a grade for a student in a specific course.
     */
    public void recordGrade(String studentId, String courseCode, Grade grade) {
        Enrollment enrollment = store.enrollments().stream()
                .filter(e -> e.getStudentId().equals(studentId) && e.getCourseCode().equals(courseCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        
        enrollment.setGrade(grade);
    }
    
    /**
     * Calculate GPA for a student.
     */
    public double calculateGPA(String studentId, Function<String, Integer> creditsLookup) {
        Transcript transcript = generateTranscript(studentId, creditsLookup);
        return transcript.computeGPA(creditsLookup);
    }
    
    /**
     * Get all students with their GPAs.
     */
    public List<StudentGPA> getAllStudentGPAs(Function<String, Integer> creditsLookup) {
        return store.students().values().stream()
                .map(student -> new StudentGPA(student, calculateGPA(student.getId(), creditsLookup)))
                .toList();
    }
    
    /**
     * Inner class for student GPA data.
     */
    public static class StudentGPA {
        private final Student student;
        private final double gpa;
        
        public StudentGPA(Student student, double gpa) {
            this.student = student;
            this.gpa = gpa;
        }
        
        public Student getStudent() { return student; }
        public double getGpa() { return gpa; }
        
        @Override
        public String toString() {
            return student.getFullName() + " (GPA: " + String.format("%.2f", gpa) + ")";
        }
    }
}

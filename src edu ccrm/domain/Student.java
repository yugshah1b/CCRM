package edu.ccrm.domain;

import edu.ccrm.interfaces.Persistable;
import edu.ccrm.interfaces.Searchable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Persistable, Searchable<Student> {
    public enum Status { ACTIVE, INACTIVE }

    private String regNo;
    private Status status;
    private final List<String> enrolledCourseCodes = new ArrayList<>();
    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    // Inner class for student statistics
    public class StudentStats {
        private final int totalEnrollments;
        private final double averageCredits;
        
        public StudentStats(int totalEnrollments, double averageCredits) {
            this.totalEnrollments = totalEnrollments;
            this.averageCredits = averageCredits;
        }
        
        public int getTotalEnrollments() { return totalEnrollments; }
        public double getAverageCredits() { return averageCredits; }
        
        @Override
        public String toString() {
            return "Stats{enrollments=" + totalEnrollments + ", avgCredits=" + averageCredits + "}";
        }
    }

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.status = Status.ACTIVE;
    }
    
    // Method overloading examples
    public Student(String id, String regNo, String fullName, String email, Status status) {
        this(id, regNo, fullName, email);
        this.status = status;
    }
    
    public Student(String id, String regNo, String fullName, String email, Status status, List<String> courses) {
        this(id, regNo, fullName, email, status);
        this.enrolledCourseCodes.addAll(courses);
    }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; this.updatedAt = LocalDateTime.now(); }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; this.updatedAt = LocalDateTime.now(); }
    public List<String> getEnrolledCourseCodes() { return enrolledCourseCodes; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    
    public StudentStats getStats() {
        return new StudentStats(enrolledCourseCodes.size(), enrolledCourseCodes.size() * 3.0);
    }

    @Override
    public String profile() {
        return "Student{" + getId() + ", regNo=" + regNo + ", status=" + status + "}";
    }
    
    // Persistable interface implementation
    @Override
    public void save(java.nio.file.Path path) throws Exception {
        String data = getId() + "," + regNo + "," + getFullName() + "," + getEmail() + "," + status;
        java.nio.file.Files.write(path, data.getBytes());
    }
    
    @Override
    public void load(java.nio.file.Path path) throws Exception {
        if (!canLoad(path)) {
            throw new java.io.FileNotFoundException("File does not exist: " + path);
        }
        String data = new String(java.nio.file.Files.readAllBytes(path));
        String[] parts = data.split(",");
        if (parts.length >= 5) {
            this.regNo = parts[1];
            this.status = Status.valueOf(parts[4]);
        }
    }
    
    // Searchable interface implementation
    @Override
    public List<Student> search(String criteria) {
        List<Student> results = new ArrayList<>();
        if (matchesCriteria(criteria)) {
            results.add(this);
        }
        return results;
    }
    
    @Override
    public List<Student> search(String... criteria) {
        List<Student> results = new ArrayList<>();
        boolean matchesAll = true;
        for (String criterion : criteria) {
            if (!matchesCriteria(criterion)) {
                matchesAll = false;
                break;
            }
        }
        if (matchesAll) {
            results.add(this);
        }
        return results;
    }
    
    public boolean matchesCriteria(String criteria) {
        String lowerCriteria = criteria.toLowerCase();
        return getFullName().toLowerCase().contains(lowerCriteria) ||
               regNo.toLowerCase().contains(lowerCriteria) ||
               getEmail().toLowerCase().contains(lowerCriteria) ||
               status.name().toLowerCase().contains(lowerCriteria);
    }
}
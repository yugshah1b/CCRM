package edu.ccrm.domain;

import java.util.Objects;

/**
 * Immutable value class for course codes.
 * Demonstrates immutability with final fields and defensive copying.
 */
public final class CourseCode {
    private final String code;
    private final String department;
    
    public CourseCode(String code, String department) {
        // Defensive copying and validation
        this.code = Objects.requireNonNull(code, "Code cannot be null").trim().toUpperCase();
        this.department = Objects.requireNonNull(department, "Department cannot be null").trim();
        
        if (this.code.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be empty");
        }
        if (this.department.isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty");
        }
    }
    
    public String getCode() {
        return code; // Safe to return - String is immutable
    }
    
    public String getDepartment() {
        return department; // Safe to return - String is immutable
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseCode that = (CourseCode) obj;
        return Objects.equals(code, that.code) && Objects.equals(department, that.department);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(code, department);
    }
    
    @Override
    public String toString() {
        return department + "-" + code;
    }
    
    /**
     * Factory method for creating course codes.
     * @param fullCode format: "CSE-101" or "101"
     * @return CourseCode instance
     */
    public static CourseCode of(String fullCode) {
        if (fullCode.contains("-")) {
            String[] parts = fullCode.split("-", 2);
            return new CourseCode(parts[1], parts[0]);
        } else {
            return new CourseCode(fullCode, "GEN");
        }
    }
}

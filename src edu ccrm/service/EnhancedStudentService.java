package edu.ccrm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import edu.ccrm.domain.Student;
import edu.ccrm.store.DataStore;

/**
 * Enhanced student service demonstrating advanced Java features.
 */
public class EnhancedStudentService {
    private final DataStore store = DataStore.getInstance();
    
    /**
     * Demonstrates enhanced control structures: for, while, do-while, break, continue, labeled jumps.
     */
    public void demonstrateControlStructures() {
        System.out.println("=== Control Structures Demo ===");
        
        // Enhanced for loop
        System.out.println("Enhanced for loop:");
        for (Student student : store.students().values()) {
            System.out.println("  " + student.getFullName());
        }
        
        // Traditional for loop with break
        System.out.println("\nTraditional for loop with break:");
        List<Student> students = new ArrayList<>(store.students().values());
        for (int i = 0; i < students.size(); i++) {
            if (i >= 3) break; // Break after 3 students
            System.out.println("  " + students.get(i).getFullName());
        }
        
        // While loop with continue
        System.out.println("\nWhile loop with continue:");
        int index = 0;
        while (index < students.size()) {
            if (students.get(index).getStatus() == Student.Status.INACTIVE) {
                index++;
                continue; // Skip inactive students
            }
            System.out.println("  Active: " + students.get(index).getFullName());
            index++;
        }
        
        // Do-while loop
        System.out.println("\nDo-while loop:");
        int count = 0;
        do {
            System.out.println("  Count: " + count);
            count++;
        } while (count < 3);
        
        // Labeled break (nested loops)
        System.out.println("\nLabeled break demo:");
        outer: for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    System.out.println("  Breaking from outer loop at i=" + i + ", j=" + j);
                    break outer;
                }
                System.out.println("  i=" + i + ", j=" + j);
            }
        }
    }
    
    /**
     * Demonstrates Arrays utilities: sorting and searching.
     */
    public void demonstrateArraysUtilities() {
        System.out.println("\n=== Arrays Utilities Demo ===");
        
        // Create array of student registration numbers
        List<Student> students = new ArrayList<>(store.students().values());
        String[] regNos = students.stream()
                .map(Student::getRegNo)
                .toArray(String[]::new);
        
        System.out.println("Original regNos: " + Arrays.toString(regNos));
        
        // Sort using Arrays.sort()
        Arrays.sort(regNos);
        System.out.println("Sorted regNos: " + Arrays.toString(regNos));
        
        // Binary search
        String searchKey = regNos.length > 0 ? regNos[0] : "REG1001";
        int index = Arrays.binarySearch(regNos, searchKey);
        System.out.println("Binary search for '" + searchKey + "': index = " + index);
        
        // Arrays.equals()
        String[] copy = Arrays.copyOf(regNos, regNos.length);
        System.out.println("Arrays equal: " + Arrays.equals(regNos, copy));
        
        // Arrays.fill()
        String[] testArray = new String[3];
        Arrays.fill(testArray, "FILLED");
        System.out.println("Filled array: " + Arrays.toString(testArray));
    }
    
    /**
     * Demonstrates upcast and downcast with instanceof checks.
     */
    public void demonstrateCasting() {
        System.out.println("\n=== Casting Demo ===");
        
        List<Student> students = new ArrayList<>(store.students().values());
        if (!students.isEmpty()) {
            Student student = students.get(0);
            
            // Upcast to Person (implicit)
            edu.ccrm.domain.Person person = student;
            System.out.println("Upcast to Person: " + person.getFullName());
            
            // Downcast with instanceof check
            if (person instanceof Student) {
                Student downcastStudent = (Student) person;
                System.out.println("Downcast to Student: " + downcastStudent.getRegNo());
            }
            
            // instanceof with different types
            System.out.println("Is Person? " + (student instanceof edu.ccrm.domain.Person));
            System.out.println("Is Student? " + (student instanceof Student));
            // Demonstrate instanceof with incompatible types (always false)
            Object obj = student;
            System.out.println("Is String? " + (obj instanceof String));
        }
    }
    
    /**
     * Demonstrates anonymous inner class for event handling.
     */
    public void demonstrateAnonymousInnerClass() {
        System.out.println("\n=== Anonymous Inner Class Demo ===");
        
        // Anonymous inner class implementing Runnable
        Runnable studentProcessor = new Runnable() {
            @Override
            public void run() {
                System.out.println("Processing students in anonymous inner class...");
                for (Student student : store.students().values()) {
                    System.out.println("  Processing: " + student.getFullName());
                }
            }
        };
        
        // Execute the anonymous inner class
        studentProcessor.run();
        
        // Anonymous inner class for event listener
        EventListener listener = new EventListener() {
            @Override
            public void onEvent(String event) {
                System.out.println("Event received: " + event);
            }
        };
        
        listener.onEvent("Student enrollment completed");
    }
    
    // Functional interface for event handling
    @FunctionalInterface
    interface EventListener {
        void onEvent(String event);
    }
    
    /**
     * Enhanced search with multiple criteria.
     */
    public List<Student> searchStudents(String... criteria) {
        List<Student> results = new ArrayList<>();
        
        for (Student student : store.students().values()) {
            boolean matchesAll = true;
            for (String criterion : criteria) {
                if (!student.matchesCriteria(criterion)) {
                    matchesAll = false;
                    break;
                }
            }
            if (matchesAll) {
                results.add(student);
            }
        }
        
        return results;
    }
    
    /**
     * Get students sorted by multiple criteria.
     */
    public List<Student> getStudentsSortedByMultipleCriteria() {
        List<Student> students = new ArrayList<>(store.students().values());
        
        // Sort by status first, then by name
        students.sort(Comparator
                .comparing(Student::getStatus)
                .thenComparing(Student::getFullName));
        
        return students;
    }
}

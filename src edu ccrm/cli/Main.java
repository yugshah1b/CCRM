package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.enums.Semester;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;
import edu.ccrm.io.FileIO;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.ReportService;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.EnhancedStudentService;
import edu.ccrm.service.TranscriptService;
import edu.ccrm.util.BackupUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppConfig config = AppConfig.getInstance();
        config.initialize();
        FileIO fileIO = new FileIO();
        BackupUtil backupUtil = new BackupUtil();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();
        ReportService reportService = new ReportService();
        EnhancedStudentService enhancedStudentService = new EnhancedStudentService();
        TranscriptService transcriptService = new TranscriptService();

        System.out.println("Welcome to Campus Course & Records Manager (CCRM)");
        System.out.println("Platform Note: Java SE focuses on core libraries and JVM runtime; EE adds enterprise APIs; ME targets constrained devices.");

        try (Scanner scanner = new Scanner(System.in)) {
        boolean running = true;
        do {
            System.out.println("\nMain Menu");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enrollment & Grades");
            System.out.println("4. Import/Export Data");
            System.out.println("5. Backup & Show Backup Size");
            System.out.println("6. Reports");
            System.out.println("7. Advanced Features Demo");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> System.out.println("Students: total = " + studentService.listAllSortedByRegNo().size());
                case "2" -> System.out.println("Courses: total = " + courseService.listAll().size());
                case "3" -> {
                    System.out.println("Enrollment quick demo: (adds sample if empty)");
                    ensureSampleData(courseService);
                    var students = studentService.listAllSortedByRegNo();
                    if (students.isEmpty()) {
                        System.out.println("No students available. Import or add first.");
                        break;
                    }
                    var s0 = students.get(0);
                    try {
                        enrollmentService.enroll(s0.getId(), "CS101");
                        System.out.println("Enrolled " + s0.getFullName() + " in CS101");
                    } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException | IllegalArgumentException e) {
                        System.out.println("Enroll skipped: " + e.getMessage());
                    }
                }
                case "4" -> {
                    System.out.println("1) Import Students  2) Import Courses  3) Export All");
                    String c = scanner.nextLine();
                    try {
                        switch (c) {
                            case "1" -> {
                                Path p = Paths.get("test-data/students.csv");
                                fileIO.importStudents(p);
                                System.out.println("Imported students from " + p);
                            }
                            case "2" -> {
                                Path p = Paths.get("test-data/courses.csv");
                                fileIO.importCourses(p);
                                System.out.println("Imported courses from " + p);
                            }
                            case "3" -> {
                                fileIO.exportStudents();
                                fileIO.exportCourses();
                                System.out.println("Exported to " + config.getExportDir());
                            }
                            default -> System.out.println("Invalid option.");
                        }
                    } catch (java.io.IOException ex) {
                        System.out.println("I/O error: " + ex.getMessage());
                    }
                }
                case "5" -> {
                    try {
                        var dest = backupUtil.backupExports();
                        long size = backupUtil.computeDirectorySize(dest);
                        System.out.println("Backup created at " + dest + " size=" + size + " bytes");
                    } catch (java.io.IOException ex) {
                        System.out.println("Backup error: " + ex.getMessage());
                    }
                }
                case "6" -> {
                    var stats = reportService.gpaStats(code -> courseService.listAll().stream()
                            .filter(cc -> cc.getCode().equals(code)).mapToInt(Course::getCredits).findFirst().orElse(0));
                    System.out.println("GPA Stats => count=" + stats.getCount() + ", avg=" + stats.getAverage());
                }
                case "7" -> {
                    System.out.println("=== Advanced Features Demo ===");
                    enhancedStudentService.demonstrateControlStructures();
                    enhancedStudentService.demonstrateArraysUtilities();
                    enhancedStudentService.demonstrateCasting();
                    enhancedStudentService.demonstrateAnonymousInnerClass();
                    
                    // Demonstrate immutable class
                    System.out.println("\n=== Immutable Class Demo ===");
                    edu.ccrm.domain.CourseCode courseCode = edu.ccrm.domain.CourseCode.of("CSE-101");
                    System.out.println("Course Code: " + courseCode);
                    System.out.println("Department: " + courseCode.getDepartment());
                    System.out.println("Code: " + courseCode.getCode());
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (running);
        }
        System.out.println("Goodbye!");
    }

    private static void ensureSampleData(CourseService courseService) {
        if (courseService.listAll().isEmpty()) {
            courseService.add(new Course.Builder().code("CS101").title("Intro to CS").credits(4)
                    .instructor("Dr. Ada").semester(Semester.FALL).department("CSE").build());
        }
    }
}



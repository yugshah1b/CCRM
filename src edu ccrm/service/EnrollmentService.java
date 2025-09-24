package edu.ccrm.service;

import java.util.List;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.enums.Grade;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;
import edu.ccrm.store.DataStore;

public class EnrollmentService {
    private final DataStore store = DataStore.getInstance();
    private final int maxCreditsPerSemester = 24; // business rule

    public void enroll(String studentId, String courseCode)
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        Student s = store.students().get(studentId);
        Course c = store.courses().get(courseCode);
        if (s == null || c == null) throw new IllegalArgumentException("Student or Course not found");

        boolean already = store.enrollments().stream()
                .anyMatch(e -> e.getStudentId().equals(studentId) && e.getCourseCode().equals(courseCode));
        if (already) throw new DuplicateEnrollmentException("Already enrolled");

        int current = store.enrollments().stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .mapToInt(e -> store.courses().get(e.getCourseCode()).getCredits())
                .sum();
        if (current + c.getCredits() > maxCreditsPerSemester) {
            throw new MaxCreditLimitExceededException("Max credits exceeded");
        }

        store.enrollments().add(new Enrollment(studentId, courseCode));
        s.getEnrolledCourseCodes().add(courseCode);
    }

    public void recordGrade(String studentId, String courseCode, Grade grade) {
        for (Enrollment e : store.enrollments()) {
            if (e.getStudentId().equals(studentId) && e.getCourseCode().equals(courseCode)) {
                e.setGrade(grade);
                return;
            }
        }
        throw new IllegalArgumentException("Enrollment not found");
    }

    public List<Enrollment> getEnrollmentsForStudent(String studentId) {
        return store.enrollments().stream().filter(e -> e.getStudentId().equals(studentId)).toList();
    }
}



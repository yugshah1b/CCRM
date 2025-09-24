package edu.ccrm.domain;

import java.util.List;

import edu.ccrm.enums.Grade;

public class Transcript {
    private final Student student;
    private final List<Enrollment> enrollments;

    public Transcript(Student student, List<Enrollment> enrollments) {
        this.student = student;
        this.enrollments = List.copyOf(enrollments);
    }

    public double computeGPA(java.util.function.Function<String, Integer> creditsLookup) {
        int totalCredits = 0;
        int totalPoints = 0;
        for (Enrollment e : enrollments) {
            Grade g = e.getGrade();
            if (g == null) continue;
            int cr = creditsLookup.apply(e.getCourseCode());
            totalCredits += cr;
            totalPoints += cr * g.getPoints();
        }
        return totalCredits == 0 ? 0.0 : (double) totalPoints / totalCredits;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transcript for ").append(student.getFullName()).append(" [").append(student.getRegNo()).append("]\n");
        for (Enrollment e : enrollments) {
            sb.append(e.getCourseCode()).append(": ").append(e.getGrade() == null ? "NA" : e.getGrade()).append('\n');
        }
        return sb.toString();
    }
}



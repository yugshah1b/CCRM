package edu.ccrm.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import edu.ccrm.enums.Semester;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

public class FileIO {
    private final AppConfig cfg = AppConfig.getInstance();
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();

    public void exportStudents() throws IOException {
        Path out = cfg.getExportDir().resolve("students.csv");
        List<String> lines = studentService.listAllSortedByRegNo().stream()
                .map(s -> String.join(",",
                        s.getId(), s.getRegNo(), s.getFullName(), s.getEmail(), s.getStatus().name()))
                .collect(Collectors.toList());
        Files.write(out, lines, StandardCharsets.UTF_8);
    }

    public void exportCourses() throws IOException {
        Path out = cfg.getExportDir().resolve("courses.csv");
        List<String> lines = courseService.listAll().stream()
                .map(c -> String.join(",",
                        c.getCode(), c.getTitle(), String.valueOf(c.getCredits()),
                        c.getInstructor(), c.getSemester().name(), c.getDepartment()))
                .toList();
        Files.write(out, lines, StandardCharsets.UTF_8);
    }

    public void importStudents(Path file) throws IOException {
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            if (line.isBlank()) continue;
            String[] parts = line.split(",");
            if (parts.length < 5) continue;
            Student s = new Student(parts[0], parts[1], parts[2], parts[3]);
            if ("INACTIVE".equalsIgnoreCase(parts[4])) s.setStatus(Student.Status.INACTIVE);
            studentService.add(s);
        }
    }

    public void importCourses(Path file) throws IOException {
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            if (line.isBlank()) continue;
            String[] p = line.split(",");
            if (p.length < 6) continue;
            Course c = new Course.Builder()
                    .code(p[0]).title(p[1]).credits(Integer.parseInt(p[2]))
                    .instructor(p[3]).semester(Semester.valueOf(p[4]))
                    .department(p[5]).build();
            courseService.add(c);
        }
    }
}



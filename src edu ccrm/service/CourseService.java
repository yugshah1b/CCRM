package edu.ccrm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.ccrm.domain.Course;
import edu.ccrm.enums.Semester;
import edu.ccrm.store.DataStore;

public class CourseService {
    private final DataStore store = DataStore.getInstance();

    public Course add(Course c) {
        store.courses().put(c.getCode(), c);
        return c;
    }

    public List<Course> listAll() {
        return new ArrayList<>(store.courses().values());
    }

    public List<Course> filterBy(String instructor, String department, Semester semester) {
        return store.courses().values().stream()
                .filter(c -> instructor == null || c.getInstructor().equalsIgnoreCase(instructor))
                .filter(c -> department == null || c.getDepartment().equalsIgnoreCase(department))
                .filter(c -> semester == null || c.getSemester() == semester)
                .collect(Collectors.toList());
    }
}



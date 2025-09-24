package edu.ccrm.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;

public final class DataStore {
    private static final DataStore INSTANCE = new DataStore();

    private final Map<String, Student> students = new HashMap<>(); // id -> student
    private final Map<String, Course> courses = new HashMap<>(); // code -> course
    private final List<Enrollment> enrollments = new ArrayList<>();

    private DataStore() { }

    public static DataStore getInstance() { return INSTANCE; }

    public Map<String, Student> students() { return students; }
    public Map<String, Course> courses() { return courses; }
    public List<Enrollment> enrollments() { return enrollments; }
}



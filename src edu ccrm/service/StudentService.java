package edu.ccrm.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.ccrm.domain.Student;
import edu.ccrm.store.DataStore;

public class StudentService {
    private final DataStore store = DataStore.getInstance();

    public Student add(Student s) {
        store.students().put(s.getId(), s);
        return s;
    }

    public Optional<Student> getById(String id) {
        return Optional.ofNullable(store.students().get(id));
    }

    public List<Student> listAllSortedByRegNo() {
        return new ArrayList<>(store.students().values()).stream()
                .sorted(Comparator.comparing(Student::getRegNo))
                .collect(Collectors.toList());
    }
}



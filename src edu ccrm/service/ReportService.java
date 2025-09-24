package edu.ccrm.service;

import edu.ccrm.domain.Transcript;
import edu.ccrm.store.DataStore;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReportService {
    private final DataStore store = DataStore.getInstance();

    public Map<String, Long> gpaDistribution(Function<String, Integer> creditsLookup) {
        return store.students().values().stream()
                .map(s -> new Transcript(s, store.enrollments().stream()
                        .filter(e -> e.getStudentId().equals(s.getId())).toList()))
                .collect(Collectors.groupingBy(
                        t -> String.format("%.1f", t.computeGPA(creditsLookup)), Collectors.counting()));
    }

    public DoubleSummaryStatistics gpaStats(Function<String, Integer> creditsLookup) {
        return store.students().values().stream()
                .map(s -> new Transcript(s, store.enrollments().stream()
                        .filter(e -> e.getStudentId().equals(s.getId())).toList()))
                .mapToDouble(t -> t.computeGPA(creditsLookup))
                .summaryStatistics();
    }
}



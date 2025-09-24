package edu.ccrm.interfaces;

import java.util.List;

/**
 * Generic interface for searchable collections.
 * Demonstrates generic interfaces and diamond problem resolution.
 * @param <T> the type of objects being searched
 */
public interface Searchable<T> {
    
    /**
     * Search for objects matching the given criteria.
     * @param criteria the search criteria
     * @return list of matching objects
     */
    List<T> search(String criteria);
    
    /**
     * Search with multiple criteria.
     * @param criteria array of search criteria
     * @return list of matching objects
     */
    List<T> search(String... criteria);
    
    /**
     * Default method for case-insensitive search.
     * Demonstrates default methods resolving diamond problem.
     * @param criteria the search criteria
     * @return list of matching objects (case-insensitive)
     */
    default List<T> searchIgnoreCase(String criteria) {
        return search(criteria.toLowerCase());
    }
    
    /**
     * Default method to get search result count.
     * @param criteria the search criteria
     * @return number of matching objects
     */
    default int searchCount(String criteria) {
        return search(criteria).size();
    }
}

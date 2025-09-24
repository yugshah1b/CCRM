package edu.ccrm.interfaces;

import java.nio.file.Path;

/**
 * Interface for objects that can be persisted to and loaded from files.
 * Demonstrates interface definition and default methods.
 */
public interface Persistable {
    
    /**
     * Save the object to a file.
     * @param path the file path to save to
     * @throws Exception if save fails
     */
    void save(Path path) throws Exception;
    
    /**
     * Load the object from a file.
     * @param path the file path to load from
     * @throws Exception if load fails
     */
    void load(Path path) throws Exception;
    
    /**
     * Default method to check if file exists before loading.
     * Demonstrates default methods in interfaces.
     * @param path the file path to check
     * @return true if file exists
     */
    default boolean canLoad(Path path) {
        return java.nio.file.Files.exists(path);
    }
    
    /**
     * Default method to get file extension.
     * @return the expected file extension
     */
    default String getFileExtension() {
        return ".dat";
    }
}

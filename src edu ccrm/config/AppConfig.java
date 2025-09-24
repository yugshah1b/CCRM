package edu.ccrm.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private Path dataRoot;
    private Path exportDir;
    private Path backupDir;

    private AppConfig() { }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public void initialize() {
        String root = System.getProperty("user.dir");
        this.dataRoot = Paths.get(root);
        this.exportDir = dataRoot.resolve("exports");
        this.backupDir = dataRoot.resolve("backups");
        try {
            Files.createDirectories(exportDir);
            Files.createDirectories(backupDir);
        } catch (IOException | SecurityException e) {
            throw new IllegalStateException("Failed to initialize directories", e);
        }
    }

    public Path getDataRoot() { return dataRoot; }
    public Path getExportDir() { return exportDir; }
    public Path getBackupDir() { return backupDir; }

    public String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
    }
}



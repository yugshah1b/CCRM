package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import edu.ccrm.config.AppConfig;

public class BackupUtil {
    private final AppConfig cfg = AppConfig.getInstance();

    public Path backupExports() throws IOException {
        Path src = cfg.getExportDir();
        Path dest = cfg.getBackupDir().resolve(cfg.timestamp());
        Files.createDirectories(dest);
        Files.walkFileTree(src, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path target = dest.resolve(src.relativize(file));
                Files.createDirectories(target.getParent());
                Files.copy(file, target);
                return FileVisitResult.CONTINUE;
            }
        });
        return dest;
    }

    public long computeDirectorySize(Path dir) throws IOException {
        if (!Files.exists(dir)) return 0L;
        final long[] size = {0L};
        Files.walkFileTree(dir, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                size[0] += Files.size(file);
                return FileVisitResult.CONTINUE;
            }
        });
        return size[0];
    }
}



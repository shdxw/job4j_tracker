package ru.job4j.filesearcher;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Arguments settings = Arguments.of(args);
        List<Path> paths = Search.search(Paths.get(settings.getDirectory()), settings.getFileType());
        LogSave.save(paths, settings.getOutput());
    }
}

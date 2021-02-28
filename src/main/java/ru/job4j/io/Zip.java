package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private String inDir;
    private String exclude;
    private String outDir;

    public Zip(String inDir, String exclude, String outDir) {
        this.inDir = inDir;
        this.exclude = exclude;
        this.outDir = outDir;
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                File file = source.toFile();
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Path> makeList() {
        File file = new File(inDir);
        //File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(exclude));
        try {
            Files.walkFileTree(file.toPath(), searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

//    public void packSingleFile(File source, File target) {
//        try (ZipOutputStream zip = new ZipOutputStream(
//        new BufferedOutputStream(
//        new FileOutputStream(target)))) {
//            zip.putNextEntry(new ZipEntry(source.getPath()));
//            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
//                zip.write(out.readAllBytes());
//            }
//            zip.closeEntry();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        ArgZip params = new ArgZip(args);
        Zip zip = new Zip(params.directory(), params.exclude(), params.output());
        List<Path> files = zip.makeList();
        zip.packFiles(files, new File(params.directory() + params.output()));
    }
}
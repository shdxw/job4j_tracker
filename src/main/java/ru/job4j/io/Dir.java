package ru.job4j.io;

import java.io.File;

public class Dir {

    private String path;

    public Dir(String path) {
        this.path = path;
    }

    public void checkDir() {
    File file = new File(path);
    //File file = new File("c:\\projects");
        if (!file.exists()) {
        throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
    }
        if (!file.isDirectory()) {
        throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
    }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
        System.out.println(subfile.getName() + " " + subfile.length());
    }
}

public void validation(String[] arguments) {
    if (arguments.length == 0) {
        throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
    }
    File file = new File(arguments[0]);
}

    public static void main(String[] args) {
        Dir directory = new Dir(args[0]);
        directory.checkDir();
    }
}

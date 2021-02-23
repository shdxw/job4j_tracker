package ru.job4j.io;

import java.io.File;

public class Dir {

    public void checkDir() {

    File file = new File("c:\\projects");
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

    public static void main(String[] args) {
        Dir directory = new Dir();
        directory.checkDir();
    }
}

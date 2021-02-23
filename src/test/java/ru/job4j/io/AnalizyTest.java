package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenOK() throws IOException {
        String in = "./src/test/java/ru/job4j/io/un1.csv";
        File target = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        analizy.unavailable(in, target.getAbsolutePath());
        assertEquals("10:57:01;10:59:01;" + System.lineSeparator() +
                "11:01:02;11:02:02;", analizy.toString(target.getAbsolutePath()));
    }

    @Test
    public void exeptionMore200() throws IOException {
        String in = "./src/test/java/ru/job4j/io/un3.csv";
        File target = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        analizy.unavailable(in, target.getAbsolutePath());
        assertEquals("10:57:01;10:59:01;" + System.lineSeparator() +
                "11:01:02;11:02:02;", analizy.toString(target.getAbsolutePath()));
    }

    @Test
    public void only200() throws IOException {
        String in = "./src/test/java/ru/job4j/io/un4.csv";
        File target = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        analizy.unavailable(in, target.getAbsolutePath());
        assertEquals("", analizy.toString(target.getAbsolutePath()));
    }

    @Test
    public void only400() throws IOException {
        String in = "./src/test/java/ru/job4j/io/un5.csv";
        File target = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        analizy.unavailable(in, target.getAbsolutePath());
        assertEquals("11:01:02;now;", analizy.toString(target.getAbsolutePath()));
    }
}
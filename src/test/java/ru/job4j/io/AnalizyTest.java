package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnalizyTest {
    @Test
    public void whenOK() {
        String in = "./src/test/java/ru/job4j/io/un1.csv";
        String out = "./src/test/java/ru/job4j/io/testOut.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(in, out);
        assertEquals("10:57:01;10:59:01;" + System.lineSeparator() +
                "11:01:02;11:02:02;", analizy.toString(out));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIllegal() {
        String in = "./src/test/java/ru/job4j/io/un2.csv";
        String out = "./src/test/java/ru/job4j/io/testOut.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(in, out);
    }

    @Test
    public void exeptionMore200() {
        String in = "./src/test/java/ru/job4j/io/un3.csv";
        String out = "./src/test/java/ru/job4j/io/testOut.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(in, out);
        assertEquals("10:57:01;10:59:01;" + System.lineSeparator() +
                "11:01:02;11:02:02;", analizy.toString(out));
    }

    @Test
    public void only200() {
        String in = "./src/test/java/ru/job4j/io/un4.csv";
        String out = "./src/test/java/ru/job4j/io/testOut.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(in, out);
        assertEquals("", analizy.toString(out));
    }

    @Test
    public void only400() {
        String in = "./src/test/java/ru/job4j/io/un5.csv";
        String out = "./src/test/java/ru/job4j/io/testOut.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(in, out);
        assertEquals("11:01:02;now;", analizy.toString(out));
    }
}
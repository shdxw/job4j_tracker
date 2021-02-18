package ru.job4j.io;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() { //
        String path = "./src/test/java/ru/job4j/io/app1.properties";
        Config config = new Config(path);
        config.load();
        //System.out.println(config.toString());
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithComment() {
        String path = "./src/test/java/ru/job4j/io/app2.properties";
        Config config = new Config(path);
        config.load();
        //System.out.println(config.toString());
        assertThat(
                config.value("//это имя коммента"),
                is(nullValue())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void nameWithoutValue() {
        String path = "./src/test/java/ru/job4j/io/app3.properties";
        Config config = new Config(path);
        config.load();
    }
}
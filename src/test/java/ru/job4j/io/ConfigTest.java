package ru.job4j.io;

import org.junit.Test;

import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() { //
        String path = "C:\\projects\\job4j_tracker\\src\\test\\java\\ru\\job4j\\io\\app1.properties";
        Config config = new Config(path);
        config.load();
        //System.out.println(config.toString());
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }

    @Test
    public void testWithComment() {
        String path = "C:\\projects\\job4j_tracker\\src\\test\\java\\ru\\job4j\\io\\app2.properties";
        Config config = new Config(path);
        config.load();
        //System.out.println(config.toString());
        assertThat(
                config.value("//это имя коммента"),
                is(nullValue())
        );
    }

    @Test
    public void nameWithoutValue() {
        String path = "C:\\projects\\job4j_tracker\\src\\test\\java\\ru\\job4j\\io\\app3.properties";
        Config config = new Config(path);
        config.load();
        //System.out.println(config.toString());
        assertThat(
                config.value("name"),
                is(nullValue())
        );
    }
}
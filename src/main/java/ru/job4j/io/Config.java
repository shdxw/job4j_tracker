package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            Pattern pattern = Pattern.compile("[a-zA-Z.0-9]+=[a-zA-Z.0-9\\s]+");
            read.lines().forEach(e -> {
                Matcher matcher = pattern.matcher(e);
                if (matcher.matches()) {
                    e = e.trim();
                    String value = e.substring(e.indexOf("=") + 1);
                    String key = e.substring(0, e.indexOf("="));
                    //System.out.println(key + " " + value);
                    values.put(key, value);
                } else {
                    throw new IllegalArgumentException();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config cf = new Config("app.properties");
        cf.load();
        System.out.println(cf.value("name"));
    }
}

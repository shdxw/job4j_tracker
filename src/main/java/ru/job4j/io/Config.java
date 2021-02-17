package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(e -> {
                if (e.matches("[a-zA-Z.0-9]+=[a-zA-Z.0-9\\s]+")) {
                    e = e.trim();
                    String value = e.substring(e.indexOf("=") + 1);
                    String key = e.substring(0, e.indexOf("="));
                    //System.out.println(key + " " + value);
                    values.put(key, value);
                } /*else {
                    System.out.println("НЕ ПОДХОДИТ!");
                }*/
            });
        } catch (Exception e) {
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

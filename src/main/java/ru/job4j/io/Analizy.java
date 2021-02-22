package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            boolean broke = false;
            StringBuilder str = new StringBuilder();
            while (read.ready()) {
                String newline = read.readLine();
                if (check(newline)) {
                    String[] position = newline.split(" ");
                    int code = Integer.parseInt(newline.split(" ")[0]);
                    String time = newline.split(" ")[1];
                    if (!broke && (code == 400 || code == 500)) {
                        str.append(time).append(";");
                        broke = true;
                    } else if (broke && (code != 400 && code != 500)) {
                        str.append(time).append(";").append(System.lineSeparator());
                        broke = false;
                    }
                }
            }
            if (broke) {
                str.append("now;");
            }
            print(str.toString(), target);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void print(String line, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
           out.print(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean check(String line) {
        Pattern pattern = Pattern.compile("^[1-9]0{2} (([0,1][0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]$");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }

    public String toString(String path) {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Analizy an = new Analizy();
        an.unavailable("./src/main/java/ru/job4j/io/unavailable.csv",
                "./src/main/java/ru/job4j/io/log.csv");
       // System.out.println(an.check("200 10:56:01"));
    }
}
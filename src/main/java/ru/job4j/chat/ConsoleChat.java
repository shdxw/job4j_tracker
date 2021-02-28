package ru.job4j.chat;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> allAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        makeAnswers();
    }

    private void makeAnswers() {
        allAnswers = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.botAnswers, StandardCharsets.UTF_8))) {
            read.lines()
                    .forEach(e -> {
                allAnswers.add(e);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String giveAnswer() {
        Random rand = new Random();
        int len = allAnswers.size();
        return allAnswers.get((len - 1) - rand.nextInt(len));
    }

    public void run() {
        String input = "";
        System.out.println("Добро пожаловать в чат, поздоровайтесь");
        Scanner scan = new Scanner(System.in);
        boolean stop = false;
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
                for (; !input.equals(OUT);) {
                    input = scan.nextLine();
                    System.out.println("You: " + input);
                    out.newLine();
                    out.append("You: ").append(input);
                    switch (input) {

                        case(STOP):
                            stop = true;
                            break;
                        case(OUT):
                            break;
                        case (CONTINUE):
                            stop = false;
                        default:
                            if (!stop) {
                                System.out.println("Bot: " + giveAnswer());
                                out.newLine();
                                out.append("Bot: ").append(giveAnswer());
                            }
                            break;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/java/ru/job4j/chat/log.txt",
                "./src/main/java/ru/job4j/chat/answers");
        cc.run();
    }
}

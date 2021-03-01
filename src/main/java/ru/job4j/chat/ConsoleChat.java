package ru.job4j.chat;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> allAnswers;

    private final Map<String, Function<String, Boolean>> dispatch = new HashMap<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        makeAnswers();
        init();
    }

    public Function<String, Boolean> toStop() {
        return msg -> {
            addAnswer(msg, "You: ");
            return true;
        };
    }

    public Function<String, Boolean> toOut() {
        return msg -> {
            addAnswer(msg, "You: ");
            return true;
        };
    }

    public Function<String, Boolean> toCon() {
        return msg -> {
            addAnswer(msg, "You: ");
            return false;
        };
    }

    public void init() {
        this.load(STOP, this.toStop());
        this.load(OUT, this.toOut());
        this.load(CONTINUE, this.toCon());
    }

    public void load(String type, Function<String, Boolean> handle) {
        this.dispatch.put(type, handle);
    }

    public boolean sent(final String msg, boolean botStop) {
        Function<String, Boolean> function = this.dispatch.get(msg);
        if (!(function == null)) {
            return function.apply(msg);
        }
        addAnswer(msg, "You: ");
        return botStop;
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

    private void addAnswer(String msg, String username) {
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            out.append(username).append(msg).append(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String giveAnswer() {
        Random rand = new Random();
        int len = allAnswers.size();
        String rsl = allAnswers.get((len - 1) - rand.nextInt(len));
        System.out.println(rsl);
        return rsl;
    }

    public void run() {
        String input = "";
        System.out.println("Добро пожаловать в чат, поздоровайтесь");
        Scanner scan = new Scanner(System.in);
        boolean stop = false;
                for (; !input.equals(OUT);) {
                    input = scan.nextLine();
                    System.out.println("You: " + input);
                    stop = sent(input, stop);
                    if (!stop) {
                        addAnswer(giveAnswer(), "Bot: ");
                    }
                }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/java/ru/job4j/chat/log.txt",
                "./src/main/java/ru/job4j/chat/answers");
        cc.run();
    }
}

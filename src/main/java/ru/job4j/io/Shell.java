package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Shell {

    private static final String START = "/";
    private static final String BACK = "..";
    private StringBuilder mainPath = new StringBuilder("/");
    private final Map<String, Consumer<String>> dispatch = new HashMap<>();

    public Shell() {
        init();
    }

    private void toGo(String way) {
        if (!mainPath.toString().equals("/")) {
            mainPath.append("/");
        }
        mainPath.append(way);
    }

    private void init() {
        this.load(START, x -> mainPath = new StringBuilder("/"));
        this.load(BACK, x -> {
            if (!mainPath.toString().equals("/")) {
                mainPath.delete(mainPath.lastIndexOf("/") + 1, mainPath.length());
                if (!mainPath.toString().equals("/")) {
                    mainPath.deleteCharAt(mainPath.length() - 1);
                }
            }
        });
    }

    private void load(String type, Consumer<String> handle) {
        this.dispatch.put(type, handle);
    }

    private String[] realSplit(String path) {
        if (path.equals("/") || path.equals("..")) {
            return new String[]{path};
        } else {
            int start = path.substring(0, 1).equals("/") ? 1 : 0;
            return path.substring(start, path.length()).split("/");
        }
    }

    public void cd(String path) {
        String[] words = realSplit(path);
        for (String word : words) {
            Consumer<String> consumer = this.dispatch.get(word);
            if (!(consumer == null)) {
                consumer.accept("");
            } else {
                toGo(word);
            }
        }
    }

    public String pwd() {
        return mainPath.toString();
    }

    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.cd("/user/..");
    }
}



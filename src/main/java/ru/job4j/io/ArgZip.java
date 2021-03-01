package ru.job4j.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgZip {
    private final String[] args;
    private final Pattern dirPat = Pattern.compile("-d=([a-zA-Z]:)?(\\[a-zA-Z0-9_]+)+\\\\?");
    private final Pattern dirEx = Pattern.compile("-e=[a-z]+");
    private final Pattern dirOut = Pattern.compile("-o=[a-zA-Z0-9_]+.[a-z]+");

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (!(args.length == 3)) {
            return false;
        }

        Matcher matcherPat = dirPat.matcher(args[0]);
        Matcher matcherEx = dirEx.matcher(args[1]);
        Matcher matcherOut = dirOut.matcher(args[2]);

        return  (matcherPat.matches() && matcherEx.matches() && matcherOut.matches());
    }

    public String directory() {
        return args[0].split("=")[1];
    }

    public String exclude() {
        return args[1].split("=")[1];
    }

    public String output() {
        return args[2].split("=")[1];
    }
}

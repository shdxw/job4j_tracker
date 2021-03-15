package ru.job4j.filesearcher;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arguments {
    private final Map<String, String> values = new HashMap<>();
    private final Pattern dirPat = Pattern.compile("-d=([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
    private final Pattern dirName = Pattern.compile("-n=([a-zA-Z0-9_*]+.[a-z]+)"); //name or regex
    private final Pattern nameType = Pattern.compile("-t=(mask|regex|name)"); //mask or regex
    private final Pattern dirOut = Pattern.compile("-o=[a-zA-Z0-9_]+.[a-z]+"); //log arg

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (!valid(args)) {
            help();
            throw new IllegalArgumentException("wrong argument's key or value");
        }
        for (String arg : args) {
            arg = arg.trim();
            int index = arg.indexOf("=");
            String key = arg.substring(0, index);
            String value = arg.substring(index + 1);
            //System.out.println(key + value);
            values.put(key, value);
        }
        String name = getFileType();
        String type = getNameType();
        if (!((name.matches("[*].[a-z]+") && type.equals("mask"))
                || (name.matches(".+") && type.equals("regex"))
                || (name.matches("[a-zA-Z0-9_]+[.][a-z]+") && type.equals("name")))) {
            help();
            throw new IllegalArgumentException("wrong pair of: " + name + " " + type);
        }
        if (getNameType().equals("mask")) {
            values.put("-n", getFileType().replace("*.", ".+[.]"));
        }
        System.out.println(toString());
    }

    public static Arguments of(String[] args) {
        Arguments names = new Arguments();
        names.parse(args);
        return names;
    }

    public boolean valid(String[] args) {
        if (!(args.length == 4)) {
            return false;
        }

        Matcher matcherPat = dirPat.matcher(args[0]);
        Matcher matcherEx = dirName.matcher(args[1]);
        Matcher matcherType = nameType.matcher(args[2]);
        Matcher matcherOut = dirOut.matcher(args[3]);

        return (matcherPat.matches() && matcherEx.matches()
                && matcherOut.matches() && matcherType.matches());
    }

    public void help() {
        System.out.println("example of launching jar: java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt \n "
                + "-d key that says there program must work \n"
                + "-n key of file's name, it's format depends on -t value (mask, name, regex) \n"
                + "-t key that show format of -n \n"
                + "-o name of log file that shows all valid files as result of working");
    }

    public String getDirectory() { //дирректория
        return get("-d");
    }

    public String getFileType() { //filename
        return get("-n");
    }

    public String getNameType() { //type of name
        return get("-t");
    }

    public String getOutput() { //output filename
        return get("-o");
    }

    @Override
    public String toString() {
        return values.toString();
    }
}

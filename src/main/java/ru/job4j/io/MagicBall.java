package ru.job4j.io;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class MagicBall {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String[] choice = {"коко", "ололо", "drom", "kapusta", "erondondon" };
        Scanner input = new Scanner(System.in);
        System.out.println("Что ты хочешь узнать?");
        input.nextLine();
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            list.add(choice[rnd.nextInt(5)]);
        }
        LogFilter.save(list, "he1.txt");
    }
}

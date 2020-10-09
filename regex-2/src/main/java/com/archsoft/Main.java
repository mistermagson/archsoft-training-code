package com.archsoft;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("src/main/resources/site.html");
            StringBuilder builder = new StringBuilder();
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                builder.append(sc.nextLine());
            }

            String textToFind = builder.toString();

            Pattern pattern = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9.\\/]*");
            showResult(pattern, textToFind);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void showResult(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            out.println(matcher.group());
        }
    }
}

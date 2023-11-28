package utils;

import data.ObjectList;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Inputter {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm" +
            ":ss");
    public static DateTimeFormatter formatterHalf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static String getString(String prompt) {
        String s;

        do {
            System.out.println(prompt);
            s = scanner.nextLine();
        } while (s.isEmpty());

        return s.trim();
    }
    public static String getString(String prompt, String regex, String errMsg) {
        String s;

        do {
            System.out.println(prompt);
            s = scanner.nextLine().trim();
            if (!s.matches(regex))
                System.out.println(errMsg);
        } while (s.isEmpty() || !s.matches(regex));

        return s.trim();
    }
    public static String getStringWithCap(String prompt) {
        return capitalizeWords(getString(prompt));
    }

    public static String getStringEmpty(String prompt) {
        String s;

        System.out.println(prompt);
        s = scanner.nextLine();

        return capitalizeWords(s.trim());
    }
    public static int getInt(String prompt) {
        int num = -1;
        do {
            try {
                String s = getString(prompt);
                num = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number");
            }
        } while (num < 0);
        return num;
    }

    public static int getInt(String prompt, int min, int max) {
        int num = -1;
        do {
            try {
                String s = getString(prompt);
                num = Integer.parseInt(s);

                if (num < min || num > max)
                    System.out.println("Please enter an integer number from " + min + " to " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number");
            }
        } while (num < min || num > max);
        return num;
    }

    public static int getIntEmpty(String prompt, int min, int max) {
        int num = -1;
        do {
            try {
                String s = getStringEmpty(prompt);
                if (s.isEmpty())
                    return -1;
                num = Integer.parseInt(s);

                if (num < min || num > max)
                    System.out.println("Please enter an integer number from " + min + " to " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number");
            }
        } while (num < min || num > max);
        return num;
    }

    public static double getDouble(String prompt) {
        double num = -1;
        do {
            try {
                String s = getString(prompt);
                num = Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a double number");
            }
        } while (num < 0);
        return num;
    }

    public static double getDouble(String prompt, double min, double max) {
        double num = -1;
        do {
            try {
                String s = getString(prompt);
                num = Double.parseDouble(s);

                if (num < min || num > max)
                    System.out.println("Please enter a double number from " + min + " to " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a double number");
            }
        } while (num < min || num > max);
        return num;
    }

    public static double getDoubleEmpty(String prompt, double min, double max) {
        double num = -1;
        do {
            try {
                String s = getStringEmpty(prompt);
                if (s.isEmpty())
                    return -1;
                num = Double.parseDouble(s);

                if (num < min || num > max)
                    System.out.println("Please enter an integer number from " + min + " to " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a double number");
            }
        } while (num < min || num > max);
        return num;
    }

    public static LocalDate getLocalDate(String prompt) {
        String date = Inputter.getString(prompt, "\\d{2}/\\d{2}/\\d{4}",
                "Please " + "enter with " + "DD/MM/YYYY format");

        return LocalDate.parse(date, formatterHalf);
    }

    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s");

        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                result.append(firstChar).append(word.substring(1)).append(" ");
            }
        }

        return result.toString().trim();
    }

    public static String generateUniqueId(String prefix, int len, ObjectList ol) {
        String id;

        do {
            int num = (int) random.nextDouble(Math.pow(10, len));
            id = prefix + String.format("%03d", num);
        } while (!Objects.isNull(ol.searchById(id)));

        return id.trim();
    }

    public static String encode(String p) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(p.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String ep) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(ep), StandardCharsets.UTF_8);
    }
}

package utils;

import data.DataRow;
import data.ObjectList;

import java.util.Arrays;

public class Visual {
    public static void printMenu(String[] options) {
        int max = Arrays.stream(options).map(String::length).max(Integer::compare).orElse(0);
        int lHalf = Math.round((float) max / 2);
        int rHalf = max % 2 == 1 ? lHalf - 1 : lHalf;

        String horizontalLine = String.format("+%" + (max + 4) + "s+", "").replaceAll(" ", "-");
        String listTitle = String.format("|%" + lHalf + "s" + "MENU" + "%" + rHalf + "s|", "", "");

        System.out.println(horizontalLine);
        System.out.println(listTitle);

        for (int i = 0, n = options.length; i < n; i++) {
            System.out.println(horizontalLine);
            System.out.printf("|%-" + (max + 4) + "s|%n",
                    (i + 1) + ". " + options[i]);
        }

        System.out.println(horizontalLine);

        System.out.printf("|%-" + (max + 4) + "s|%n",
                (options.length + 1) + ". " + "Others - Quit");

        System.out.println(horizontalLine);
    }

    public static void printDataList(ObjectList ol, String[] columnNames, String title) {
        // Construct padding of each column
        int[] lens = new int[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].matches("(?i)(^.*(id|quantity|sex|weight|role|birthdate|color).*$)")) {
                lens[i] = columnNames[i].length() + 6;
                continue;
            }
            lens[i] = columnNames[i].length() + 18;
        }

        // Find maximum len and construct padding for the title row
        int max = Arrays.stream(lens).sum();
        int lHalf = Math.round((float) max / 2);
        int rHalf = max % 2 == 1 ? lHalf - 1 : lHalf;
        int tLen = title.length();

        // Format separate row and title row
        String listTitle = String.format("|%" + lHalf + "s" + title + "%" + rHalf + "s|", "", "");
        String horizontalLine = String.format("+%" + (max + tLen) + "s+", "").replaceAll(" ", "-");

        // Add padding to the last column
        StringBuilder horizontalLineWithCol = new StringBuilder("+");
        for (int i = 0, n = lens.length; i < n; i++) {
            String col = String.format("%" + 1 + "$" + (lens[i]) + "s+", "").replaceAll(" ", "-"), next;
            if (i == n - 1) {
                do {
                    next = horizontalLineWithCol + col;
                    if (next.length() < horizontalLine.length()) {
                        col = "-" + col;
                        lens[i]++;
                    }
                } while (next.length() < horizontalLine.length());
            }
            horizontalLineWithCol.append(col);
        }

        // Print title row
        System.out.println(horizontalLine);
        System.out.println(listTitle);
        System.out.println(horizontalLine);

        // Construct and print column names row
        StringBuilder namesRow = new StringBuilder("|");
        for (int i = 0; i < columnNames.length; i++) {
            namesRow.append(String.format("%" + 1 + "$" + lens[i] + "s|", columnNames[i]));
        }
        System.out.println(namesRow);
        System.out.println(horizontalLineWithCol);

        for (Object d : ol) {
            System.out.println(((DataRow) d).toStringRow(lens));
            System.out.println(horizontalLineWithCol);
        }
    }
}

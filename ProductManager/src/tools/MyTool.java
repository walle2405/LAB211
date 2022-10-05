package tools;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.io.IOException;

public class MyTool {
    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        if (str.matches(regEx)) return true;
        return false;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == 'A');
    }

    public static double parseDouble(String doubleStr) {
        String dStr = doubleStr.trim();
        return Double.parseDouble(dStr);
    }

    public static int parseInt(String intStr) {
        String iStr = intStr.trim();
        return Integer.parseInt(iStr);
    }

    public static int readRangeInt(String message, int min, int max){
        int input = 0;
        do {
            System.out.print(message + ": ");
            Scanner SC = new Scanner(System.in);
            input = SC.nextInt();
        } while (input <= min && input > max);
        return input;
    }

    public static double readRangeDouble(String message, double min, double max){
        double input = 0;
        do {
            System.out.print(message + ": ");
            Scanner SC = new Scanner(System.in);
            input = SC.nextDouble();
        } while (input <= min && input > max);
        return input;
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern, String errorMessage){
        String input = "";
        boolean valid;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
            if (!valid) System.out.println(errorMessage);
        } while (!valid);
        return input;
    }

    public static List<String> readLinesFromFile(String filename) {
        ArrayList<String> list;
        list = new ArrayList();
        File f = new File(filename);
        if (f.exists()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);
                String line;
                while ((line = bf.readLine()) != null) {
                    if (!line.equals("")) {
                        list.add(line);
                    }
                }
                bf.close();
                fr.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        return list;
    }

    public static void writeFile(String filename, List list) {
        if (!list.isEmpty()) {
            try {
                File file = new File(filename);
                FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw);
                for (Object item : list) {
                    pw.print(item);
                }
                pw.close();
                fw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static String readStatus(String message) {
        String input;
        boolean valid;
        do {
            System.out.print(message + ": ");
            Scanner SC = new Scanner(System.in);
            input = SC.nextLine().trim();
            valid = (input.equalsIgnoreCase("Available") || input.equalsIgnoreCase("Not Available"));
        } while (!valid);
        return input;
    }
}

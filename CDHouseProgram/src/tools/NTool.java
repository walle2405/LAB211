package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class NTool {

    public static List<String> readData() throws IOException {
        List<String> list = new ArrayList<String>();
        DataInputStream input = new DataInputStream(new FileInputStream("DataFile/catalog.dat"));
        while (input.available() > 0) {
            String s = input.readUTF();
            s.trim();
            if (!s.isEmpty()) {
                list.add(s);
            }
        }
        input.close();
        return list;
    }

    public static void writeData(List list) throws IOException {
        if (list.size() == 0) {
            System.out.println("Empty list.");
        }
        DataOutputStream output = new DataOutputStream(new FileOutputStream("DataFile/catalog.dat"));
        for (int i = 0; i < list.size(); i++) {
            output.writeUTF(list.get(i).toString());
        }
        output.close();
        System.out.println("Finished writing to file.");
    }

    public static String readPattern(String msg, String pattern) {
        String inputRaw, input = "", sub;
        boolean valid;

        do {
            Scanner SC = new Scanner(System.in);
            System.out.print(msg);
            inputRaw = SC.nextLine();
            if (inputRaw.isEmpty()) {
                System.out.println("Input must not be empty.");
                valid = false;
            } else if (inputRaw.contains("CD") || inputRaw.contains("cd") || inputRaw.contains("Cd") || inputRaw.contains("cD")) {
                input = inputRaw.toUpperCase();
                valid = validStr(input, pattern);
            } else {
                sub = inputRaw.substring(0, 1).toUpperCase();
                input = sub + inputRaw.substring(1);
                valid = validStr(input, pattern);
            }
        } while (!valid);
        return input;
    }

    public static boolean validStr(String input, String pattern) {
        if (input.matches(pattern)) {
            return true;
        } else {
            System.out.println("Invalid input, try again");
            return false;
        }
    }

    public static String readNonPattern(String msg) {
        String input = "";

        do {
            Scanner SC = new Scanner(System.in);
            System.out.print(msg);
            input = SC.nextLine();
        } while (input.isEmpty());
        return input;
    }
    
}

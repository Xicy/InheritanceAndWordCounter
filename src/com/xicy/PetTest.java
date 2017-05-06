package com.xicy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PetTest {
    private static PetCollection petCollection = new PetCollection();

    //region Helpers
    private static String[] translateCommandline(String toProcess) {
        if (toProcess == null || toProcess.length() == 0) return new String[0];

        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = normal;
        final StringTokenizer tok = new StringTokenizer(toProcess, "\"\' ", true);
        final ArrayList<String> result = new ArrayList<>();
        final StringBuilder current = new StringBuilder();
        boolean lastTokenHasBeenQuoted = false;

        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            switch (state) {
                case inQuote:
                    if ("\'".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                case inDoubleQuote:
                    if ("\"".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                default:
                    if ("\'".equals(nextTok)) {
                        state = inQuote;
                    } else if ("\"".equals(nextTok)) {
                        state = inDoubleQuote;
                    } else if (" ".equals(nextTok)) {
                        if (lastTokenHasBeenQuoted || current.length() != 0) {
                            result.add(current.toString());
                            current.setLength(0);
                        }
                    } else {
                        current.append(nextTok);
                    }
                    lastTokenHasBeenQuoted = false;
                    break;
            }
        }
        if (lastTokenHasBeenQuoted || current.length() != 0) {
            result.add(current.toString());
        }
        if (state == inQuote || state == inDoubleQuote) {
            throw new RuntimeException("unbalanced quotes in " + toProcess);
        }
        return result.toArray(new String[result.size()]);
    }

    private static void printLine(String text, int size) {
        String lr = new String(new char[(size - text.length()) / 2]).replace("\0", "-");
        System.out.println(lr + text + lr);
    }

    private static boolean checkArgs(String[] args, int argCount) {
        return args.length == argCount + 1;
    }
    //endregion

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] command;
        printHelp();
        do {
            command = translateCommandline(scanner.nextLine());
            if (command.length < 1) continue;
            command[0] = command[0].toLowerCase();
            if (command[0].equals("exit")) break;
            switch (command[0]) {
                case "help":
                    printHelp();
                    break;
                case "add":
                    add(command);
                    break;
                case "remove":
                    remove(command);
                    break;
                case "list":
                    list(command);
                    break;
                case "avg":
                    avg();
                    break;
                default:
                    System.out.println("Wrong Command Please Check Help");
                    printHelp();
                    break;
            }
        } while (true);
    }

    private static void printHelp() {
        printLine("Help", 80);
        System.out.println("add dog <Name:String> <Weight:Integer>\t\tAdd dog in list");
        System.out.println("add cat <Name:String> <CoatColor:String>\tAdd cat in list");
        System.out.println("remove <Name:String>\t\t\t\t\t\tRemove pet by name");
        System.out.println("list\t\t\t\t\t\t\t\t\t\tList all pets");
        System.out.println("list <PetType:Dog/Cat>\t\t\t\t\t\tList all dogs or cats");
        System.out.println("avg\t\t\t\t\t\t\t\t\t\t\tShow statistics of dogs' weight");
        System.out.println("help\t\t\t\t\t\t\t\t\t\tShow help text");
        System.out.println("exit\t\t\t\t\t\t\t\t\t\tClose to program");
        printLine("", 80);
    }

    private static void add(String[] args) {
        if (checkArgs(args, 3))
            if (args[1].toLowerCase().equals("dog") && isNumeric(args[3]))
                petCollection.add(new Dog(args[2], Integer.parseInt(args[3])));
            else if (args[1].toLowerCase().equals("cat"))
                petCollection.add(new Cat(args[2], args[3]));
            else System.out.println("Error:Argument is wrong\\nPlease check Help");
        else System.out.println("Error:Argument is wrong\\nPlease check Help");
    }

    private static void remove(String[] args) {
        if (checkArgs(args, 1))
            petCollection.remove(args[1]);
        else System.out.println("Error:Argument is wrong\\nPlease check Help");
    }

    private static void list(String[] args) {
        printLine("List of Pets", 80);
        Map<String, Pet> map = null;
        if (checkArgs(args, 0))
            map = petCollection.listAll();
        else if (checkArgs(args, 1))
            if (args[1].toLowerCase().equals("dog"))
                map = petCollection.listDogs();
            else if (args[1].toLowerCase().equals("cat"))
                map = petCollection.listCats();
            else System.out.println("Error: Only uses dog or cat.");
        else System.out.println("Error:Argument is wrong\\nPlease check Help");

        for (Map.Entry<String, Pet> p : map.entrySet())
            System.out.println(p.getValue());
        printLine("", 80);
    }

    private static void avg() {
        printLine("Statistics of Dogs", 80);
        Map<String, Pet> pets = petCollection.listDogs();
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE, sum = 0, count = 0, w = 0;
        for (Map.Entry<String, Pet> entry : pets.entrySet()) {
            w = ((Dog) entry.getValue()).getWeight();
            if (w > high) {
                high = w;
            }
            if (w < low) {
                low = w;

            }
            sum += w;
            count++;
        }
        System.out.println("Min Weight : " + low);
        System.out.println("Max Weight : " + high);
        System.out.println("Average : " + sum / (float) count);
        printLine("", 80);
    }
}

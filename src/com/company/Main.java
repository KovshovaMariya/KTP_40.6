package com.company;

import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        InputStreamReader isr = new InputStreamReader(System.in);
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Введите номер задания (для завершения выберите 0):");
            int number = in.nextInt();
            switch (number) {
                case 0: {
                    isExit = true;
                    break;
                }
                case 1: {
                    System.out.println(One("Test essay", 2, 6));
                    break;
                }
                case 2: {
                    System.out.println(Two("((()))(())()()(()())"));
                    break;
                }
                case 3: {
                    System.out.println(toCamelCase("hello_edabit"));
                    System.out.println(toSnakeCase("helloEdabit"));
                }
                case 4: {
                    System.out.println(Four(16, 18, 30, 1.8));
                    break;
                }
                case 5: {
                    System.out.println(Five("205 pounds", "73 inches"));
                    break;
                }
                case 6: {
                    System.out.println(bugger(999));
                    break;
                }
                case 7: {
                    System.out.println(Seven("abbccc"));
                }
                case 8: {
                    System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
                    break;
                }
                case 9: {
                    System.out.println(Nine(451999277, 411777899));
                    break;
                }
                case 10: {
                    System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", "$"));
                    break;
                }
            }
        }
    }
    public static List<String> One(String essay, int wordsNumber, int lineLength) {
        String[] words = essay.split(" ");
        List<String> result = new LinkedList<String>();
        int remaining = lineLength;
        String str = "";
        for (String word : words) {
            if (remaining >= word.length()) {
                str += word;
                remaining -= word.length();
            } else {
                result.add(str);
                str = word;
                remaining = lineLength - word.length();
            }
        }
        result.add(str);
        return result;
    }
    public static List<String> Two(String brackets) {
        List<String> result = new LinkedList<String>();
        int count = 0;
        String buffer = "";
        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(')
                count++;
            else if (brackets.charAt(i) == ')')
                count--;

            buffer += brackets.charAt(i);
            if (count == 0) {
                result.add(buffer);
                buffer = "";
            }
        }
        return result;
    }
    public static String toCamelCase(String str) {
        String[] parts = str.split("_");
        return parts[0] + parts[1].substring(0, 1).toUpperCase() + parts[1].substring(1);
    }

    public static String toSnakeCase(String str) {
        String first = "", second = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == Character.toUpperCase(str.charAt(i))) {
                first = str.substring(0, i);
                second = str.substring(i);
                break;
            }
        }
        return first + "_" + second.toLowerCase();
    }
    public static double Four(double start, double end, double rate, double modifier) {
        if (start <= 17.0) {
            if (end > 17.0) {
                return (17.0 - start) * rate + (end - 17.0) * rate * modifier;
            } else {
                return (end - start) * rate;
            }
        } else {
            return (end - start) * rate * modifier;
        }
    }
    public static String Five(String weight, String height) {
        String[] Weight = weight.split(" ");
        String[] Height = height.split(" ");
        double ParsedWeight = Double.parseDouble(Weight[0]);
        double ParsedHeight = Double.parseDouble(Height[0]);
        final double kilosToPounds = 2.20462;
        final double metersToInches = 39.3701;
        if (Weight[1].equals("pounds"))
            ParsedWeight /= kilosToPounds;
        if (Height[1].equals("inches"))
            ParsedHeight /= metersToInches;
        double result = ParsedWeight / ParsedHeight / ParsedHeight;
        result = (double) (java.lang.Math.round(result * 10)) / 10;
        String category = "";
        if (result < 18.5)
            category = "Underweight";
        else if (result < 25)
            category = "Normal weight";
        else
            category = "Overweight";
        return Double.toString(result) + " " + category;
    }
    public static int bugger(int num) {
        int count = 1;
        while (Integer.toString(multiplyDigits(num)).length() != 1) {
            num = multiplyDigits(num);
            count++;
        }
        return count;
    }

    public static int multiplyDigits(int num) {
        if (num == 0)
            return 0;
        int result = 1;
        while (num > 0) {
            result *= num % 10;
            num /= 10;
        }
        return result;
    }
    public static String Seven(String str) {
        int count = 1;
        String result = "";
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1))
                count++;
            else {
                if (count > 1) {
                    result += str.charAt(i) + "*" + Integer.toString(count);
                    count = 1;
                } else {
                    result += str.charAt(i);
                }
            }
        }
        if (str.charAt(str.length() - 1) == str.charAt(str.length() - 2))
            result += str.charAt(str.length() - 1) + "*" + Integer.toString(count);
        else result += str.charAt(str.length() - 1);
        return result;
    }
    public static boolean doesRhyme(String str1, String str2) {
        String[] words1 = str1.split(" ");
        String[] words2 = str2.split(" ");

        return takeVowels(words1[words1.length - 1]).equals(takeVowels(words2[words2.length - 1]));
    }

    public static String takeVowels(String str) {
        String vowels = "AEIOUaeiou";
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            if (vowels.contains(Character.toString(str.charAt(i))))
                result += str.charAt(i);
        }
        return result;
    }
    public static boolean Nine(long num1, long num2) {
        for (int i = 1; i < 10; i++) {
            if (Long.toString(num1).contains(Integer.toString(i) + Integer.toString(i) + Integer.toString(i))) {
                if (Long.toString(num2).contains(Integer.toString(i) + Integer.toString(i)))
                    return true;
            }

        }
        return false;
    }

    public static int countUniqueBooks(String str, String separator) {
        String special = "[$&+,:;=?@#|'<>.-^*()%!]";
        if (special.contains(separator))
            separator = Character.toString('\\') + separator;
        String[] books = str.split(separator);
        int sum = 0;

        for (int i = 1; i < books.length; i += 2) {
            sum += countUniqueChars(books[i]);
        }
        return sum;
    }

    private static int countUniqueChars(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i == str.lastIndexOf(str.charAt(i)))
                result++;
        }
        return result;
    }

}
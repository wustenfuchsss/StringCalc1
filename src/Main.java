
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение:");
            String input = scanner.nextLine();
            String output = calculate(input);
            System.out.println(output);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Некорректно введено выражение");
        }
    }
    public static String calculate(String input) {
        String result = null;
        String[] ex = input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        String el1 = ex[0];
        String op = ex[1];
        String el2 = ex[2];

        try {
            int n1 = Integer.parseInt(el1);
            throw new RuntimeException("Первым аргументом должна быть строка");
        } catch (NumberFormatException e) {
            try {
                String n1 = el1.replace("\"", "");
                if (n1.length() > 10) {
                    throw new RuntimeException("Строка не должна быть длиннее 10 символов");
                }
                int n2 = Integer.parseInt(el2);
                if (n2 > 10) {
                    throw new RuntimeException("Число не может быть больше 10");
                }
                result = "\"" + str2Int(n1, op, n2) + "\"";
            } catch (NumberFormatException f) {
                String n1 = el1.replace("\"", "");
                String n2 = el2.replace("\"", "");
                if (n1.length() > 10 || n2.length() > 10) {
                    throw new RuntimeException("Строка не должна быть длиннее 10 символов");
                }
                result = "\"" + str2Str(n1, op, n2) + "\"";
            }
        }
        if (result.length() > 40) {
            result = result.substring(0, 41) + "...\"";
        }
        return result;
    }
    public static String str2Int(String n1, String op, int n2) {
        String res = null;
        switch (op) {
            case "*":
                res = n1.repeat(n2);
                break;
            case "/":
                int length = n1.length() / n2;
                res = n1.substring(0, length);
                break;
            default:
                throw new RuntimeException("Использован некорректный оператор");
        }
        return res;
    }
    public static String str2Str(String n1, String op, String n2) {
        String res = null;
        switch (op) {
            case "+":
                res = n1 + n2;
                break;
            case "-":
                if (n1.contains(n2)) {
                    res = n1.replace(n2, "");
                } else {
                    res = n1;
                }
                break;
            default:
                throw new RuntimeException("Использован некорректный оператор");
        }
        return res;
    }
}

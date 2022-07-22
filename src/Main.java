import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();   // Считали строку (условие) и обрезали пробельные символы в начале и конце
        String first = "";                          // Задаём переменные, которые будем считывать, когда распарсю.
        char operator = ' ';
        String second = "";
        int count = 0;                          // счётчик создал, чтобы считать кавычки и пробелы
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);              // Создали переменную и распарсили строку по индексам
            // System.out.println(symbol);
            if (symbol == '"') {
                count++;

            } else if (count == 1) {
                first = first + symbol;

            } else if (symbol == ' ') {
                count++;
            } else if (count == 3) {                // первые кавычки, вторые кавычки, пробел.
                operator = symbol;
            } else if (count >= 4) {                //
                second = second + symbol;
            }

        }
//        System.out.println(first);
//        System.out.println(operator);
//        System.out.println(second);
        boolean secondNumber = count <= 4;
        validate(first, operator, second, secondNumber);
        String result = calculate(first, operator, second);
        if (result.length() > 40) {
            result = result.substring(0, 41) + "...";
        }
        System.out.println(result);
    }

    private static String calculate(String first, char operator, String second) {
        switch (operator) {
            case '+':
//                if (secondNumber) {
//                    throw new RuntimeException("error");
//                }
                return first + second;
            case '-':
//                if (secondNumber) {
//                    throw new RuntimeException("operator is wrong");
//                }
                return first.replace(second, "");
            case '*':
//                if (!secondNumber) {
//                    throw new RuntimeException("operator is wrong");
//
//                }
                return first.repeat(Integer.parseInt(second.trim()));  // Берём строковую переменную и преобразовываем её в число
            case '/':
//                if (!secondNumber) {
//                    throw new RuntimeException("operator is wrong");
//                }
                int divider = Integer.parseInt(second.trim());
                return first.substring(0, first.length() / divider);
            default:
                throw new RuntimeException("unsupported operator");
        }
    }

    private static void validate(String first, char operator, String second, boolean secondNumber) {
        switch (operator) {
            case '+':
            case '-':
                if (secondNumber) {
                    throw new RuntimeException("error");
                }
                if (first.length() > 10 || second.length() > 10) {
                    throw new RuntimeException("string is big");
                }
                break;
            case '*':
            case '/':
                if (!secondNumber) {
                    throw new RuntimeException("operator is wrong");
                }
                if (Integer.parseInt(second) > 10) {
                    throw new RuntimeException("number is big");
                }
                if (first.length() > 10) {
                    throw new RuntimeException("string is big");
                }
        }
    }


}
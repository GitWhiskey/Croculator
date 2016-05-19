package main;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private SimpleFraction a;
    private SimpleFraction b;
    private String operation;
    private final static String OUTPUT_FILE_PATH = "results.xml";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        if(args.length == 1) {
            calculator.runFromFile(args[0]);
        } else {
            calculator.run();
        }
    }

    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                try {
                    System.out.println("Введите выражение в формате 'дробь_1 [знак операции] дробь_2'");
                    processExpression(br, null);
                    System.out.println("Еще раз? (y/n)");
                    if(br.readLine().equals("n")) {
                        break;
                    }
                } catch (InvalidExpressionException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runFromFile(String filepath) {
        int line = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE_PATH)))) {
            while(br.ready()) {
                try {
                    line++;
                    processExpression(br, bw);
                } catch (InvalidExpressionException e) {
                    String output = "Выражение "+line+": "+e.getMessage();
                    bw.write(output);
                    bw.newLine();
                    System.out.println(output);
                }
            }
            System.out.println("Результат был записан в файл.");
        } catch (IOException e) {
            System.out.println("Путь к файлу указан неверно, или такого файла не существует.");
        }
    }

    private void processExpression(BufferedReader br, BufferedWriter bw) throws IOException {
        String expression = br.readLine();
        parseExpression(expression);
        SimpleFraction answer = null;
        switch (operation) {
            case "+":
                answer = SimpleFraction.add(a, b);
                break;
            case "-":
                answer = SimpleFraction.subtract(a, b);
                break;
            case "*":
                answer = SimpleFraction.multiply(a, b);
                break;
            case "/":
                answer = SimpleFraction.divide(a, b);
                break;
        }
        if(answer != null) {
            if(bw != null) {
                bw.write(answer.toString());
                bw.newLine();
            }
            System.out.println(expression + " = " + answer.toString());
        }
    }

    private void parseExpression(String expr) throws InvalidExpressionException {
        expr = expr.replaceAll(" ", "");
        //Разделяем все выражения на группы чисел (от знака до знака)
        Pattern p = Pattern.compile("/|\\+|-|\\*");
        Matcher m = p.matcher(expr);

        try {
            m.find();
            if (!m.group().equals("/")) throw new InvalidExpressionException("Ошибка в первом операнде.");
            //"a" numerator
            int an = Integer.parseInt(expr.substring(0, m.start()).trim());
            //Начало знаменателя - конец знака дроби
            int adStart = m.end();

            m.find();
            operation = m.group();
            //"a" denominator
            int ad = Integer.parseInt(expr.substring(adStart, m.start()));
            if(ad == 0) throw new InvalidExpressionException("Первая дробь содержит ноль в знаменателе.");
            int bnStart = m.end();

            m.find();
            if (!m.group().equals("/")) throw new InvalidExpressionException("Ошибка во втором операнде.");
            //"b" numerator
            int bn = Integer.parseInt(expr.substring(bnStart, m.start()));
            int bdStart = m.end();

            m.find();
            //"b" denominator
            int bd = Integer.parseInt(expr.substring(bdStart));
            if(bd == 0) throw new InvalidExpressionException("Вторая дробь содержит ноль в знаменателе.");

            a = new SimpleFraction(an, ad);
            b = new SimpleFraction(bn, bd);
        } catch (NumberFormatException nfe) {
            throw new InvalidExpressionException("Дроби содержат недопустимые символы");
//        } catch (Exception e) {
//            throw new InvalidExpressionException("Выражение содержит недопустимые символы или недоустимые" +
//                    " математические операции.");
        }
    }

    private class InvalidExpressionException extends RuntimeException {
        private String message;

        public InvalidExpressionException(String message) {
            super();
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

}

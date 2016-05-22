import java.io.*;

public class Calculator {

    private ExpressionParser parser = new ExpressionParser();
    private final static String OUTPUT_FILE_PATH = "results.xml";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        if(args.length == 1) {
            calculator.runFromFile(args[0]);
        } else {
            calculator.run();
        }
    }

    /**
     * Основной метод работы калькулятора без использования файла.
     */
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
                } catch (InvalidExpressionException | ArithmeticException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Методы работы калькулятора. Вызывается, когда в аргументах командной строки указан путь к файлу.
     * @param filepath - путь к файлу
     */
    public void runFromFile(String filepath) {
        int line = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE_PATH)))) {
            while(br.ready()) {
                try {
                    line++;
                    processExpression(br, bw);
                } catch (InvalidExpressionException | ArithmeticException e) {
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

    /**
     * Обработка одного выражения.
     * @param br - ридер для считывания строки выражения.
     * @param bw - зписывает в файл. Если файл не задействован, оставить null.
     * @throws IOException
     */
    private void processExpression(BufferedReader br, BufferedWriter bw) throws IOException, ArithmeticException,
                                                                                            InvalidExpressionException {
        String expression = br.readLine();
        parser.parse(expression);
        SimpleFraction a = parser.getA();
        SimpleFraction b = parser.getB();
        SimpleFraction answer = null;
        switch (parser.getOperation()) {
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

}

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
            System.out.println("Возникла ошибка при чтении с консоли.");
        }
    }

    /**
     * Методы работы калькулятора. Вызывается, когда в аргументах командной строки указан путь к файлу.
     * @param filepath - путь к файлу
     */
    public void runFromFile(String filepath) {
        int line = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)))) {
            ExpressionWriter writer = new ExpressionWriter();
            writer.createDocument();
            while(br.ready()) {
                try {
                    line++;
                    processExpression(br, writer);
                } catch (InvalidExpressionException | ArithmeticException e) {
                    writer.addException(e, line);
                }
            }
            writer.writeXML(OUTPUT_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Путь к файлу указан неверно, или такого файла не существует.");
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Возникла ошибка при создании XML файла.");
        }
    }

    /**
     * Обработка одного выражения.
     * @param br - ридер для считывания строки выражения.
     * @param ew - зписывает в файл. Если файл не задействован, оставить null.
     * @throws IOException
     */
    private void processExpression(BufferedReader br, ExpressionWriter ew) throws IOException, ArithmeticException,
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
            if(ew != null) {
                ew.addResult(answer.toString());
            } else {
                System.out.println(expression + " = " + answer.toString());
            }
        }
    }
}

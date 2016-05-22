import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
    private SimpleFraction a;
    private SimpleFraction b;
    private String operation;

    /**
     * Парсит строковое выражение переводит дроби в формат SimpleFraction и определяет операцию.
     * Проверяет входные данные на корректность.
     * @param expr
     * @throws InvalidExpressionException
     */
    public void parse(String expr) throws InvalidExpressionException {
        expr = expr.replaceAll(" ", "");
        //Разделяем все выражения на группы чисел (от знака до знака)
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(expr);

        try {
            m.find();
            //"a" numerator
            int an = Integer.parseInt(m.group());
            if(expr.charAt(m.end()) != '/') throw new InvalidExpressionException("Ошибка в первом операнде.");
            m.find();
            //"a" denominator
            int ad = Integer.parseInt(m.group());
            operation = String.valueOf(expr.charAt(m.end()));
            if(!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/")) {
                throw new InvalidExpressionException("Выражение содержит недопустимый символ операции.");
            }
            m.find();
            //"b" numerator
            int bn = Integer.parseInt(m.group());
            if(expr.charAt(m.end()) != '/') throw new InvalidExpressionException("Ошибка во втором операнде.");
            m.find();
            //"b" denominator
            int bd = Integer.parseInt(m.group());

            a = new SimpleFraction(an, ad);
            b = new SimpleFraction(bn, bd);
        } catch (NumberFormatException nfe) {
            throw new InvalidExpressionException("Дроби содержат недопустимые символы");
        } catch (IllegalStateException | StringIndexOutOfBoundsException e) {
            throw new InvalidExpressionException("Выражение содержит недопустимые символы или недоустимые" +
                    " математические операции.");
        }
    }

    public SimpleFraction getA() {
        return a;
    }

    public SimpleFraction getB() {
        return b;
    }

    public String getOperation() {
        return operation;
    }
}

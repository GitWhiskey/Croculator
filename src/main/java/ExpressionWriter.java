import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


/**
 * Класс для записи данных в файл в формате XML.
 * Следует использовать так:
 *  1. Сначала создать объект документа при помощи createDocument().
 *  2. Записать туда вычисленные результаты при помощи addResult() или сообщения об ошибках при помощи addException().
 *  3. Записать полученные документ при помощи метода writeXML().
 */
public class ExpressionWriter {

    private Document doc;
    private Element results;

    public ExpressionWriter() {}

    /**
     * Создает объект документа и корневой элемент results, в который будут заносится результаты.
     * @throws ParserConfigurationException
     */
    public void createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();
        results = doc.createElement("results");
        doc.appendChild(results);
    }

    /**
     * Создает элемент "result" и заносит в него результат операции над простыми дробями.
     * @param answer - результат операции над дробями.
     */
    public void addResult(String answer) {
        Element result = doc.createElement("result");
        result.appendChild(doc.createTextNode(answer));
        results.appendChild(result);
    }

    /**
     * Создает элемент "exception" и записывает в него сообщение об ошибке, полученное от исключения.
     * @param e - исключение
     * @param line - номер строки выражения, которое породило исключение
     */
    public void addException(Exception e, int line) {
        String output = "Выражение "+line+": "+e.getMessage();
        Element exceptionElement = doc.createElement("exception");
        exceptionElement.appendChild(doc.createTextNode(output));
        results.appendChild(exceptionElement);
    }

    /**
     * Трансформирует созданный объект документа в XML файл по указанному пути.
     * @param filepath - путь к XML файлу
     * @throws TransformerException
     */
    public void writeXML(String filepath) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
        System.out.println("Результат был записан в файл.");
    }
}

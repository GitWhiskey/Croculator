public class InvalidExpressionException extends RuntimeException {
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

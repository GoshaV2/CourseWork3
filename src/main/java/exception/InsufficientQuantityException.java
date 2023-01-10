package exception;

public class InsufficientQuantityException extends Exception{
    private final int gettingQuantity;
    private final int existingQuantity;

    public InsufficientQuantityException(int gettingQuantity, int existingQuantity) {
        this.gettingQuantity = gettingQuantity;
        this.existingQuantity = existingQuantity;
    }

    public InsufficientQuantityException(String message, int gettingQuantity, int existingQuantity) {
        super(message);
        this.gettingQuantity = gettingQuantity;
        this.existingQuantity = existingQuantity;
    }
}

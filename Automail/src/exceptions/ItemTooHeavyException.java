package exceptions;

/**
 * This exception is thrown when a robot takes a NormalMailItem from its StorageTube which is too heavy for that robot
 */
public class ItemTooHeavyException extends Exception {
    public ItemTooHeavyException(){
        super("Item too heavy! Dropped by robot.");
    }
}

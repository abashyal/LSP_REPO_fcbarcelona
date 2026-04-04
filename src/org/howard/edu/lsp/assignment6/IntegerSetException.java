package org.howard.edu.lsp.assignment6;

/**
 * Exception thrown when an illegal operation is performed on an IntegerSet,
 * such as calling largest() or smallest() on an empty set.
 */
public class IntegerSetException extends RuntimeException {

    /**
     * Constructs an IntegerSetException with the given message.
     *
     * @param message description of the error
     */
    public IntegerSetException(String message) {
        super(message);
    }
}

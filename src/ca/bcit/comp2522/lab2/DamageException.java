package ca.bcit.comp2522.lab2;

/**
 * Thrown when an invalid damage amount is applied.
 *
 * @author YourName
 * @version 1.0
 */
public class DamageException extends RuntimeException
{
    /**
     * Constructs a DamageException with a message.
     *
     * @param message message
     */
    public DamageException(final String message)
    {
        super(message);
    }
}

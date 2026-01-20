package ca.bcit.comp2522.lab2;

/**
 * Thrown when an Orc cannot go berserk due to insufficient rage.
 *
 * @author YourName
 * @version 1.0
 */
public class LowRageException extends RuntimeException
{
    /**
     * Constructs a LowRageException with a message.
     *
     * @param message message
     */
    public LowRageException(final String message)
    {
        super(message);
    }
}

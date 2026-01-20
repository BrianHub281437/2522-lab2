package code;

/**
 * Thrown when an invalid damage amount is applied.
 *
 * @author Ziad Malik
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

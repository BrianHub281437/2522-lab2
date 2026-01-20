package code;

/**
 * Thrown when an invalid healing amount is applied.
 *
 * @author Ziad Malik
 * @version 1.0
 */
public class HealingException extends RuntimeException
{
    /**
     * Constructs a HealingException with a message.
     *
     * @param message message
     */
    public HealingException(final String message)
    {
        super(message);
    }
}

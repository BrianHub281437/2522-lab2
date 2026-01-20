package code;
/**
 * Thrown when an Orc cannot go berserk due to insufficient rage.
 *
 * @author Ziad Malik
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

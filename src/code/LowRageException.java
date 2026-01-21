package code;

/**
 * Thrown when an Orc attempts to go berserk with insufficient rage.
 * This is an unchecked exception that extends RuntimeException.
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class LowRageException extends RuntimeException
{
    /**
     * Constructs a LowRageException with a descriptive error message.
     *
     * @param message the error message describing the rage shortage
     */
    public LowRageException(final String message)
    {
        super(message);
    }
}
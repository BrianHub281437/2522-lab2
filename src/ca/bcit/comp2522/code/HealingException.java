package ca.bcit.comp2522.code;

/**
 * Thrown when an invalid healing amount is applied to a creature.
 * This is an unchecked exception that extends RuntimeException.
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class HealingException extends RuntimeException
{
    /**
     * Constructs a HealingException with a descriptive error message.
     *
     * @param message the error message describing why the healing is invalid
     */
    public HealingException(final String message)
    {
        super(message);
    }
}
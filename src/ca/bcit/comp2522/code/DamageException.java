package ca.bcit.comp2522.code;

/**
 * Thrown when an invalid damage amount is applied to a creature.
 * This is an unchecked exception that extends RuntimeException.
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class DamageException extends RuntimeException
{
    /**
     * Constructs a DamageException with a descriptive error message.
     *
     * @param message the error message describing why the damage is invalid
     */
    public DamageException(final String message)
    {
        super(message);
    }
}
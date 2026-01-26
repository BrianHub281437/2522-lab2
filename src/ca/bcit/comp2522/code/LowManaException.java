package ca.bcit.comp2522.code;

/**
 * Thrown when an Elf attempts to cast a spell with insufficient mana.
 * This is a checked exception.
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class LowManaException extends Exception
{
    /**
     * Constructs a LowManaException with a descriptive error message.
     *
     * @param message the error message describing the mana shortage
     */
    public LowManaException(final String message)
    {
        super(message);
    }
}
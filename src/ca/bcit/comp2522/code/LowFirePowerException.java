package ca.bcit.comp2522.code;

/**
 * Thrown when a Dragon attempts to breathe fire with insufficient firepower.
 * This is a checked exception.
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class LowFirePowerException extends Exception
{
    /**
     * Constructs a LowFirePowerException with a descriptive error message.
     *
     * @param message the error message describing the firepower shortage
     */
    public LowFirePowerException(final String message)
    {
        super(message);
    }
}
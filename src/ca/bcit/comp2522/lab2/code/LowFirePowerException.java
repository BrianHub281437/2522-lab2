package ca.bcit.comp2522.lab2;

/**
 * Thrown when a Dragon tries to breathe fire with insufficient firepower.
 *
 * @author YourName
 * @version 1.0
 */
public class LowFirePowerException extends Exception
{
    /**
     * Constructs a LowFirePowerException with a message.
     *
     * @param message message
     */
    public LowFirePowerException(final String message)
    {
        super(message);
    }
}

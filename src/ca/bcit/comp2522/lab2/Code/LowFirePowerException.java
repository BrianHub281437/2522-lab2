package code;
/**
 * Thrown when a Dragon tries to breathe fire with insufficient firepower.
 *
 * @author Ziad Malik
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

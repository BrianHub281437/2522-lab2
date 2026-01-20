package ca.bcit.comp2522.lab2;

/**
 * Thrown when an Elf tries to cast a spell with insufficient mana.
 *
 * @author YourName
 * @version 1.0
 */
public class LowManaException extends Exception
{
    /**
     * Constructs a LowManaException with a message.
     *
     * @param message message
     */
    public LowManaException(final String message)
    {
        super(message);
    }
}

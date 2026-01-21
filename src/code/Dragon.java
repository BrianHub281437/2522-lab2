package code;

import java.util.Date;

/**
 * A Dragon is a child of Creature
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class Dragon extends Creature
{
    private static final int MIN_FIRE_POWER = 0;
    private static final int MAX_FIRE_POWER = 100;

    private static final int FIRE_POWER_COST = 10;
    private static final int FIRE_DAMAGE = 20;

    private int firePower;

    /**
     * Constructs a Dragon with the specified attributes.
     * All parameters are validated to ensure they meet the required constraints.
     *
     * @param name        the dragon's name
     * @param dateOfBirth the dragon's date of birth
     * @param health      the dragon's initial health
     * @param firePower   the dragon's initial firepower (must be between MIN_FIRE_POWER and MAX_FIRE_POWER inclusive)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Dragon(final String name,
                  final Date dateOfBirth,
                  final int health,
                  final int firePower)
    {
        super(name, dateOfBirth, health);

        validateFirePower(firePower);

        this.firePower = firePower;
    }

    /**
     * Returns the dragon's current firepower level.
     *
     * @return the current firepower (MIN_FIRE_POWER to MAX_FIRE_POWER)
     */
    public final int getFirePower()
    {
        return firePower;
    }

    /**
     * Returns a formatted string containing the dragon's details.
     * Overrides the Creature getDetails() method to include firepower information.
     *
     * @return a formatted string with dragon details including firepower
     */
    @Override
    public String getDetails()
    {
        return String.format("%s, FirePower=%d", super.getDetails(), firePower);
    }

    /**
     * Commands the dragon to breathe fire at a target creature.
     * Breathing fire costs FIRE_POWER_COST firepower and deals FIRE_DAMAGE damage to the target.
     * The dragon must have at least FIRE_POWER_COST firepower to breathe fire.
     *
     * @param target the creature to attack with fire (must not be null)
     * @throws LowFirePowerException if firepower is less than the cost to breathe fire
     * @throws IllegalArgumentException if target is null
     */
    public void breatheFire(final Creature target) throws LowFirePowerException
    {
        validateTarget(target);

        if (firePower < FIRE_POWER_COST)
        {
            throw new LowFirePowerException("Not enough fire power to breathe fire. FirePower=" + firePower);
        }

        firePower -= FIRE_POWER_COST;
        target.takeDamage(FIRE_DAMAGE);
    }

    /**
     * Restores the dragon's firepower by the specified amount.
     * Firepower cannot exceed MAX_FIRE_POWER; if restoration would raise firepower above 100,
     * firepower is capped at MAX_FIRE_POWER.
     *
     * @param amount the amount of firepower to restore (must be non-negative)
     * @throws IllegalArgumentException if amount is negative
     */
    public void restoreFirePower(final int amount)
    {
        if (amount < MIN_FIRE_POWER)
        {
            throw new IllegalArgumentException("Restore amount cannot be negative: " + amount);
        }

        firePower += amount;

        if (firePower > MAX_FIRE_POWER)
        {
            firePower = MAX_FIRE_POWER;
        }
    }

    /**
     * Validates that the firepower value is within the acceptable range.
     *
     * @param firePower the firepower value to validate
     * @throws IllegalArgumentException if firepower is not between MIN_FIRE_POWER and MAX_FIRE_POWER inclusive
     */
    private static void validateFirePower(final int firePower)
    {
        if (firePower < MIN_FIRE_POWER || firePower > MAX_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power out of range (" + MIN_FIRE_POWER + ".." + MAX_FIRE_POWER + "): " + firePower);
        }
    }

    /**
     * Validates that the target creature is not null.
     *
     * @param target the target creature to validate
     * @throws IllegalArgumentException if target is null
     */
    private static void validateTarget(final Creature target)
    {
        if (target == null)
        {
            throw new IllegalArgumentException("Target must not be null.");
        }
    }
}
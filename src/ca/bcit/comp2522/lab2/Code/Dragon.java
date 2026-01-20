package code;

import java.util.Date;

/**
 * A Dragon is a Creature with firepower.
 *
 * @author Ziad Malik
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
     * Constructs a Dragon.
     *
     * @param name        name (must not be null/blank)
     * @param dateOfBirth dob (must not be in the future)
     * @param health      health (1..100)
     * @param firePower   firepower (0..100)
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
     * Returns current firepower.
     *
     * @return firepower
     */
    public final int getFirePower()
    {
        return firePower;
    }

    /**
     * Returns details including firepower.
     *
     * @return details string
     */
    @Override
    public String getDetails()
    {
        return String.format("%s, FirePower=%d", super.getDetails(), firePower);
    }

    /**
     * Breathes fire at a target, costing firepower and dealing damage.
     *
     * @param target target creature
     * @throws LowFirePowerException if firepower is insufficient
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
     * Restores firepower by amount, capped at 100.
     *
     * @param amount amount to restore (must be >= 0)
     * @throws IllegalArgumentException if amount is negative
     */
    public void restoreFirePower(final int amount)
    {
        if (amount < 0)
        {
            throw new IllegalArgumentException("Restore amount cannot be negative: " + amount);
        }

        firePower += amount;

        if (firePower > MAX_FIRE_POWER)
        {
            firePower = MAX_FIRE_POWER;
        }
    }

    private static void validateFirePower(final int firePower)
    {
        if (firePower < MIN_FIRE_POWER || firePower > MAX_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power out of range (" + MIN_FIRE_POWER + ".." + MAX_FIRE_POWER + "): " + firePower);
        }
    }

    private static void validateTarget(final Creature target)
    {
        if (target == null)
        {
            throw new IllegalArgumentException("Target must not be null.");
        }
    }
}

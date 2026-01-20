package ca.bcit.comp2522.lab2;

import java.util.Date;

/**
 * An Orc is a Creature with rage.
 *
 * @author YourName
 * @version 1.0
 */
public class Orc extends Creature
{
    private static final int MIN_RAGE = 0;
    private static final int MAX_RAGE = 30;

    private static final int RAGE_INCREASE = 5;
    private static final int MIN_RAGE_TO_BERSERK = 5;

    private static final int BASE_BERSERK_DAMAGE = 15;
    private static final int DOUBLE_BERSERK_DAMAGE = 30;

    private static final int DOUBLE_DAMAGE_RAGE_THRESHOLD = 20;

    private int rage;

    /**
     * Constructs an Orc.
     *
     * @param name        name (must not be null/blank)
     * @param dateOfBirth dob (must not be in the future)
     * @param health      health (1..100)
     * @param rage        rage (0..30)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Orc(final String name,
               final Date dateOfBirth,
               final int health,
               final int rage)
    {
        super(name, dateOfBirth, health);

        validateRage(rage);

        this.rage = rage;
    }

    /**
     * Returns current rage.
     *
     * @return rage
     */
    public final int getRage()
    {
        return rage;
    }

    /**
     * Returns details including rage.
     *
     * @return details string
     */
    @Override
    public String getDetails()
    {
        return String.format("%s, Rage=%d", super.getDetails(), rage);
    }

    /**
     * Goes berserk: requires at least 5 rage, increases rage by 5,
     * then deals damage (30 if rage exceeds 20 after the increase, else 15).
     *
     * @param target target creature
     * @throws LowRageException if rage is below 5
     * @throws IllegalArgumentException if target is null
     */
    public void berserk(final Creature target)
    {
        final int damage;

        validateTarget(target);

        if (rage < MIN_RAGE_TO_BERSERK)
        {
            throw new LowRageException("Not enough rage to berserk. Rage=" + rage);
        }

        rage += RAGE_INCREASE;

        if (rage > MAX_RAGE)
        {
            rage = MAX_RAGE;
        }

        if (rage > DOUBLE_DAMAGE_RAGE_THRESHOLD)
        {
            damage = DOUBLE_BERSERK_DAMAGE;
        }
        else
        {
            damage = BASE_BERSERK_DAMAGE;
        }

        target.takeDamage(damage);
    }

    private static void validateRage(final int rage)
    {
        if (rage < MIN_RAGE || rage > MAX_RAGE)
        {
            throw new IllegalArgumentException("Rage out of range (" + MIN_RAGE + ".." + MAX_RAGE + "): " + rage);
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

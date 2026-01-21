package code;

import java.util.Date;

/**
 * Orc is a child of Creature
 *
 * @author Ziad Malik
 * @author Brian Lau
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
     * Constructs an Orc with the specified attributes.
     * All parameters are validated to ensure they meet the required constraints.
     *
     * @param name        the orc's name
     * @param dateOfBirth the orc's date of birth
     * @param health      the orc's initial health
     * @param rage        the orc's initial rage (must be between MIN_RAGE and MAX_RAGE inclusive)
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
     * Returns the orc's current rage level.
     *
     * @return the current rage (MIN_RAGE to MAX_RAGE)
     */
    public final int getRage()
    {
        return rage;
    }

    /**
     * Returns a formatted string containing the orc's details.
     * Overrides the Creature getDetails() method to include rage information.
     *
     * @return a formatted string with orc details including rage
     */
    @Override
    public String getDetails()
    {
        return String.format("%s, Rage=%d", super.getDetails(), rage);
    }

    /**
     * Commands the orc to go berserk and attack a target creature.
     * Going berserk requires at least MIN_RAGE_TO_BESERK rage and increases rage by RAGE_INCREASE.
     * If rage exceeds DOUBLE_DAMAGE_RAGE_THRESHOLD after the increase, deals DOUBLE_BESERK_DAMAGE damage; otherwise deals BASE_BESERK_DAMAGE damage.
     *
     * @param target the creature to attack in a berserk rage (must not be null)
     * @throws LowRageException if rage is less than the minimum required to berserk
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

    /**
     * Validates that the rage value is within the acceptable range.
     *
     * @param rage the rage value to validate
     * @throws IllegalArgumentException if rage is not between MIN_RAGE and MAX_RAGE inclusive
     */
    private static void validateRage(final int rage)
    {
        if (rage < MIN_RAGE || rage > MAX_RAGE)
        {
            throw new IllegalArgumentException("Rage out of range (" + MIN_RAGE + ".." + MAX_RAGE + "): " + rage);
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
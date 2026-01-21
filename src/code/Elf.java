package code;

import java.util.Date;

/**
 * An Elf is a child of Creature
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class Elf extends Creature
{
    private static final int MIN_MANA = 0;
    private static final int MAX_MANA = 50;

    private static final int SPELL_MANA_COST = 5;
    private static final int SPELL_DAMAGE = 10;

    private int mana;

    /**
     * Constructs an Elf with the specified attributes.
     * All parameters are validated to ensure they meet the required constraints.
     *
     * @param name        the elf's name
     * @param dateOfBirth the elf's date of birth
     * @param health      the elf's initial health
     * @param mana        the elf's initial mana (must be between MIN_MANA and MAX_MANA inclusive)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Elf(final String name,
               final Date dateOfBirth,
               final int health,
               final int mana)
    {
        super(name, dateOfBirth, health);

        validateMana(mana);

        this.mana = mana;
    }

    /**
     * Returns the elf's current mana level.
     *
     * @return the current mana (MIN_MANA to MAX_MANA)
     */
    public final int getMana()
    {
        return mana;
    }

    /**
     * Returns a formatted string containing the elf's details.
     * Overrides the Creature getDetails() method to include mana information.
     *
     * @return a formatted string with elf details including mana
     */
    @Override
    public String getDetails()
    {
        return String.format("%s, Mana=%d", super.getDetails(), mana);
    }

    /**
     * Commands the elf to cast a spell at a target creature.
     * Casting a spell costs SPELL_MANA_COST mana and deals SPELL_DAMAGE damage to the target.
     * The elf must have at least SPELL_MANA_COST mana to cast a spell.
     *
     * @param target the creature to attack with magic (must not be null)
     * @throws LowManaException if mana is less than the cost to cast a spell
     * @throws IllegalArgumentException if target is null
     */
    public void castSpell(final Creature target) throws LowManaException
    {
        validateTarget(target);

        if (mana < SPELL_MANA_COST)
        {
            throw new LowManaException("Not enough mana to cast spell. Mana=" + mana);
        }

        mana -= SPELL_MANA_COST;
        target.takeDamage(SPELL_DAMAGE);
    }

    /**
     * Restores the elf's mana by the specified amount.
     * Mana cannot exceed 50; if restoration would raise mana above 50,
     * mana is capped at 50.
     *
     * @param amount the amount of mana to restore (must be non-negative)
     * @throws IllegalArgumentException if amount is negative
     */
    public void restoreMana(final int amount)
    {
        if (amount < MIN_MANA)
        {
            throw new IllegalArgumentException("Restore amount cannot be negative: " + amount);
        }

        mana += amount;

        if (mana > MAX_MANA)
        {
            mana = MAX_MANA;
        }
    }

    /**
     * Validates that the mana value is within the acceptable range.
     *
     * @param mana the mana value to validate
     * @throws IllegalArgumentException if mana is not between MIN_MANA and MAX_MANA inclusive
     */
    private static void validateMana(final int mana)
    {
        if (mana < MIN_MANA || mana > MAX_MANA)
        {
            throw new IllegalArgumentException("Mana out of range (" + MIN_MANA + ".." + MAX_MANA + "): " + mana);
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
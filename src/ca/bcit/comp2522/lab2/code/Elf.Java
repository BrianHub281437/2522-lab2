package ca.bcit.comp2522.lab2;

import java.util.Date;

/**
 * An Elf is a Creature with mana.
 *
 * @author YourName
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
     * Constructs an Elf.
     *
     * @param name        name (must not be null/blank)
     * @param dateOfBirth dob (must not be in the future)
     * @param health      health (1..100)
     * @param mana        mana (0..50)
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
     * Returns current mana.
     *
     * @return mana
     */
    public final int getMana()
    {
        return mana;
    }

    /**
     * Returns details including mana.
     *
     * @return details string
     */
    @Override
    public String getDetails()
    {
        return String.format("%s, Mana=%d", super.getDetails(), mana);
    }

    /**
     * Casts a spell at a target, costing mana and dealing damage.
     *
     * @param target target creature
     * @throws LowManaException if mana is insufficient
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
     * Restores mana by amount, capped at 50.
     *
     * @param amount amount to restore (must be >= 0)
     * @throws IllegalArgumentException if amount is negative
     */
    public void restoreMana(final int amount)
    {
        if (amount < 0)
        {
            throw new IllegalArgumentException("Restore amount cannot be negative: " + amount);
        }

        mana += amount;

        if (mana > MAX_MANA)
        {
            mana = MAX_MANA;
        }
    }

    private static void validateMana(final int mana)
    {
        if (mana < MIN_MANA || mana > MAX_MANA)
        {
            throw new IllegalArgumentException("Mana out of range (" + MIN_MANA + ".." + MAX_MANA + "): " + mana);
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

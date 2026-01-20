package code;

import java.util.Calendar;
import java.util.Date;

/**
 * Models a generic fantasy creature with a name, birthdate, and health.
 *
 * @author Ziad Malik
 * @version 1.0
 */
public class Creature
{
    private static final int MIN_HEALTH = 1;
    private static final int MAX_HEALTH = 100;
    private static final int DEAD_HEALTH = 0;

    private final String name;
    private final Date dateOfBirth;
    private int health;

    /**
     * Constructs a Creature.
     *
     * @param name        creature name (must not be null/blank)
     * @param dateOfBirth date of birth (must not be in the future)
     * @param health      initial health (must be 1..100)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Creature(final String name,
                    final Date dateOfBirth,
                    final int health)
    {
        validateName(name);
        validateDateOfBirth(dateOfBirth);
        validateHealth(health);

        this.name = name;
        this.dateOfBirth = new Date(dateOfBirth.getTime());
        this.health = health;
    }

    /**
     * Returns the creature's name.
     *
     * @return name
     */
    public final String getName()
    {
        return name;
    }

    /**
     * Returns a defensive copy of the creature's birthdate.
     *
     * @return date of birth
     */
    public final Date getDateOfBirth()
    {
        return new Date(dateOfBirth.getTime());
    }

    /**
     * Returns the current health.
     *
     * @return health
     */
    public final int getHealth()
    {
        return health;
    }

    /**
     * Returns true if the creature is alive.
     *
     * @return true if health is greater than 0
     */
    public final boolean isAlive()
    {
        return health > DEAD_HEALTH;
    }

    /**
     * Reduces health by damage. Health cannot go below 0.
     *
     * @param damage damage amount (must be >= 0)
     * @throws DamageException if damage is negative
     */
    public void takeDamage(final int damage)
    {
        if (damage < 0)
        {
            throw new DamageException("Damage cannot be negative: " + damage);
        }

        health -= damage;

        if (health < DEAD_HEALTH)
        {
            health = DEAD_HEALTH;
        }
    }

    /**
     * Increases health by healAmount. Health cannot exceed 100.
     *
     * @param healAmount healing amount (must be >= 0)
     * @throws HealingException if healing amount is negative
     */
    public void heal(final int healAmount)
    {
        if (healAmount < 0)
        {
            throw new HealingException("Healing cannot be negative: " + healAmount);
        }

        health += healAmount;

        if (health > MAX_HEALTH)
        {
            health = MAX_HEALTH;
        }
    }

    /**
     * Calculates the creature's age in years (based on dateOfBirth).
     *
     * @return age in years (>= 0)
     */
    public final int getAgeYears()
    {
        final Calendar today;
        final Calendar birth;
        final int years;

        today = Calendar.getInstance();
        birth = Calendar.getInstance();

        birth.setTime(dateOfBirth);

        years = calculateAgeYears(today, birth);

        return years;
    }

    /**
     * Returns a formatted details string (name, birthdate, age, health).
     *
     * @return details string
     */
    public String getDetails()
    {
        return String.format("Class=%s, Name=%s, DOB=%s, AgeYears=%d, Health=%d",
                getClass().getSimpleName(),
                name,
                dateOfBirth,
                getAgeYears(),
                health);
    }

    private static int calculateAgeYears(final Calendar today,
                                         final Calendar birth)
    {
        final int yearDiff;
        final int todayDayOfYear;
        final int birthDayOfYear;
        int years;

        yearDiff = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        birthDayOfYear = birth.get(Calendar.DAY_OF_YEAR);

        years = yearDiff;

        if (todayDayOfYear < birthDayOfYear)
        {
            years--;
        }

        if (years < 0)
        {
            years = 0;
        }

        return years;
    }

    private static void validateName(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name must not be null/blank.");
        }
    }

    private static void validateDateOfBirth(final Date dateOfBirth)
    {
        final Date now;

        if (dateOfBirth == null)
        {
            throw new IllegalArgumentException("Date of birth must not be null.");
        }

        now = new Date();

        if (dateOfBirth.after(now))
        {
            throw new IllegalArgumentException("Date of birth must not be in the future: " + dateOfBirth);
        }
    }

    private static void validateHealth(final int health)
    {
        if (health < MIN_HEALTH || health > MAX_HEALTH)
        {
            throw new IllegalArgumentException("Health out of range (" + MIN_HEALTH + ".." + MAX_HEALTH + "): " + health);
        }
    }
}

package code;

import java.util.Calendar;
import java.util.Date;

/**
 * Models a fantasy creature with a name, birthdate, and health.
 * This is the superclass for specific creature types such as Dragons, Elves, and Orcs.
 * Creatures can take damage, heal, and provide details about themselves.
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class Creature
{
    private static final int MIN_HEALTH = 1;
    private static final int MAX_HEALTH = 100;
    private static final int DEAD_HEALTH = 0;
    private static final int MIN_DAMAGE = 0;

    private final String name;
    private final Date dateOfBirth;
    private static final int MIN_AGE_YEAR = 0;
    private int health;

    /**
     * Constructs a Creature with the specified attributes.
     * All parameters are validated to ensure they meet the required constraints.
     *
     * @param name        the creature's name (must not be null or blank)
     * @param dateOfBirth the creature's date of birth (must not be null or in the future)
     * @param health      the creature's initial health (must be between DEAD_HEALTH and MAX_HEALTH inclusive)
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
     * @return the name of the creature
     */
    public final String getName()
    {
        return name;
    }

    /**
     * Returns a defensive copy of the creature's birthdate.
     * A defensive copy is returned to prevent external modification of the internal date.
     *
     * @return a copy of the creature's date of birth
     */
    public final Date getDateOfBirth()
    {
        return new Date(dateOfBirth.getTime());
    }

    /**
     * Returns the creature's current health value.
     *
     * @return the current health (DEAD_HEALTH to MAX_HEALTH)
     */
    public final int getHealth()
    {
        return health;
    }

    /**
     * Checks if the creature is currently alive.
     *
     * @return true if health is greater than DEAD_HEALTH, false otherwise
     */
    public final boolean isAlive()
    {
        return health > DEAD_HEALTH;
    }

    /**
     * Reduces the creature's health by the specified damage amount.
     * Health cannot go below DEAD_HEALTH; if damage would reduce health below DEAD_HEALTH, health is set to DEAD_HEALTH.
     *
     * @param damage the amount of damage to inflict (must be non-negative)
     * @throws DamageException if damage is negative
     */
    public void takeDamage(final int damage)
    {
        if (damage < MIN_DAMAGE)
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
     * Increases the creature's health by the specified healing amount.
     * Health cannot exceed MAX_HEALTH; if healing would raise health above MAX_HEALTH, health is capped at MAX_HEALTH.
     *
     * @param healAmount the amount of health to restore (must be non-negative)
     * @throws HealingException if healAmount is negative
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
     * Calculates and returns the creature's age in years based on its date of birth.
     * The age is calculated by comparing the birthdate to the current date.
     *
     * @return the creature's age in years (MIN_AGE_YEAR or greater)
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
     * Returns a formatted string containing the creature's details.
     * The details include the class type, name, date of birth, age in years, and current health.
     *
     * @return a formatted string with creature details
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

    /**
     * Calculates the age in years between a birthdate and today's date.
     * Accounts for whether the birthday has occurred yet this year.
     *
     * @param today the current date calendar
     * @param birth the birthdate calendar
     * @return the age in years (MIN_AGE_YEAR or greater)
     */
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

        if (years < MIN_AGE_YEAR)
        {
            years = MIN_AGE_YEAR;
        }

        return years;
    }

    /**
     * Validates that the provided name is not null or blank.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if name is null or blank
     */
    private static void validateName(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name must not be null or blank.");
        }
    }

    /**
     * Validates that the provided date of birth is not null and not in the future.
     *
     * @param dateOfBirth the date of birth to validate
     * @throws IllegalArgumentException if dateOfBirth is null or in the future
     */
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

    /**
     * Validates that the health value is within the acceptable range.
     *
     * @param health the health value to validate
     * @throws IllegalArgumentException if health is not between MIN_HEALTH and MAX_HEALTH inclusive
     */
    private static void validateHealth(final int health)
    {
        if (health < MIN_HEALTH || health > MAX_HEALTH)
        {
            throw new IllegalArgumentException("Health out of range (" + MIN_HEALTH + ".." + MAX_HEALTH + "): " + health);
        }
    }
}
package ca.bcit.comp2522.lab2;

import java.util.Calendar;
import java.util.Date;

public abstract class Creature {

    private static final int MAX_HEALTH = 100;
    private static final int MIN_HEALTH = 1;
    private static final int DEAD = 0;

    private final String name;
    private final Date dateOfBirth;
    private int health;

    public Creature(final String name, final Date dateOfBirth, final int health) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }

        if (dateOfBirth == null) {
            throw new IllegalArgumentException("date of birth cannot be null");
        }

        Date today = new Date(); // current date/time
        if (dateOfBirth.after(today)) {
            throw new IllegalArgumentException("date of birth cannot be in the future");
        }

        if (health < MIN_HEALTH || health > MAX_HEALTH) {
            throw new IllegalArgumentException("Health must be between 1 and 100");
        }

        this.name = name;
        this.dateOfBirth = new Date(dateOfBirth.getTime()); // defensive copy
        this.health = health;
    }

    public boolean isAlive() {
        return health > DEAD;
    }

    public void takeDamage(final int damage) throws damageException {
        if (damage < DEAD) {
            throw new damageException("damage cannot be negative");
        }

        health -= damage;

        if (health < DEAD) {
            health = DEAD;
        }
    }

    public void heal(final int healAmount) throws healException {
        if (healAmount < DEAD) {
            throw new healException("healing amount cannot be negative");
        }

        health += healAmount;

        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    public int getAgeYears() {
        Calendar today = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        // If birthday hasn't happened yet this year, subtract 1
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int dobDayOfYear = dob.get(Calendar.DAY_OF_YEAR);
        if (todayDayOfYear < dobDayOfYear) {
            age--;
        }

        return age;
    }

    public void getDetails() {
        System.out.println("Name: " + name);
        System.out.println("Date of birth: " + dateOfBirth);
        System.out.println("Age (years): " + getAgeYears());
        System.out.println("Health: " + health);
    }
}
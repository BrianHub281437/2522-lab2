package tests;

import code.Creature;
import code.DamageException;
import code.Dragon;
import code.Elf;
import code.HealingException;
import code.LowFirePowerException;
import code.LowManaException;
import code.LowRageException;
import code.Orc;

import java.util.Date;

/**
 * Test driver for Creature, Dragon, Elf, and Orc classes.
 *
 * @author Ziad Malik
 * @author Brian Lau
 * @version 1.0
 */
public class CreatureTest
{
    private static final int DRAGON_HEALTH = 90;
    private static final int DRAGON_FIRE_POWER = 15;

    private static final int ELF_HEALTH = 70;
    private static final int ELF_MANA = 8;

    private static final int ORC_HEALTH = 85;
    private static final int ORC_RAGE = 4;

    /**
     * Program entry point.
     * Creates creatures and demonstrates polymorphism, runtime type checking, and combat.
     *
     * @param args command line arguments (not used)
     */
    public static void main(final String[] args)
    {
        final Creature c1;
        final Creature c2;
        final Creature c3;

        c1 = new Dragon("Smolder", new Date(), DRAGON_HEALTH, DRAGON_FIRE_POWER);
        c2 = new Elf("Elowen", new Date(), ELF_HEALTH, ELF_MANA);
        c3 = new Orc("Gruk", new Date(), ORC_HEALTH, ORC_RAGE);

        System.out.println("=== Demonstrating Polymorphism with getDetails() ===");
        printDetailsPolymorphic(c1);
        printDetailsPolymorphic(c2);
        printDetailsPolymorphic(c3);

        System.out.println("\n=== Demonstrating Runtime Type Checking ===");
        showRuntimeTypes(c1);
        showRuntimeTypes(c2);
        showRuntimeTypes(c3);

        System.out.println("\n=== Demonstrating Combat with Exception Handling ===");
        makeCreaturesFight(c1, c2, c3);
    }

    /**
     * Demonstrates polymorphism by calling getDetails() on a Creature reference
     * that may actually be a Dragon, Elf, or Orc at runtime.
     *
     * @param creature the creature whose details to print
     */
    private static void printDetailsPolymorphic(final Creature creature)
    {
        System.out.println(creature.getDetails());
    }

    /**
     * Demonstrates runtime type checking using both getClass().getSimpleName()
     * and the instanceof operator.
     *
     * @param creature the creature whose runtime type to check
     */
    private static void showRuntimeTypes(final Creature creature)
    {
        final String simpleName;

        simpleName = creature.getClass().getSimpleName();

        System.out.println("getClass().getSimpleName() = " + simpleName);

        if (creature instanceof Dragon)
        {
            System.out.println("instanceof Dragon = true");
        }
        else if (creature instanceof Elf)
        {
            System.out.println("instanceof Elf = true");
        }
        else if (creature instanceof Orc)
        {
            System.out.println("instanceof Orc = true");
        }
        else
        {
            System.out.println("instanceof (Dragon/Elf/Orc) = false");
        }
    }

    /**
     * Demonstrates combat between creatures with proper exception handling.
     * Shows handling of both checked exceptions (LowFirePowerException, LowManaException)
     * and unchecked exceptions (LowRageException, DamageException, HealingException).
     *
     * @param c1 the first creature (expected to be a Dragon)
     * @param c2 the second creature (expected to be an Elf)
     * @param c3 the third creature (expected to be an Orc)
     */
    private static void makeCreaturesFight(final Creature c1,
                                           final Creature c2,
                                           final Creature c3)
    {
        final Dragon dragon;
        final Elf elf;
        final Orc orc;

        dragon = (Dragon)c1;
        elf = (Elf)c2;
        orc = (Orc)c3;

        // Dragon breathes fire on Orc - demonstrates checked exception handling
        try
        {
            dragon.breatheFire(orc);
            System.out.println("Dragon breathed fire on Orc.");
        }
        catch (final LowFirePowerException e)
        {
            System.out.println("Dragon could not breathe fire: " + e.getMessage());
        }
        catch (final DamageException e)
        {
            System.out.println("Damage problem during fire attack: " + e.getMessage());
        }
        catch (final RuntimeException e)
        {
            System.out.println("Unexpected runtime issue during fire attack: " + e.getMessage());
        }

        // Elf casts spell on Dragon - demonstrates checked exception handling
        try
        {
            elf.castSpell(dragon);
            System.out.println("Elf cast a spell on Dragon.");
        }
        catch (final LowManaException e)
        {
            System.out.println("Elf could not cast spell: " + e.getMessage());
        }
        catch (final DamageException e)
        {
            System.out.println("Damage problem during spell: " + e.getMessage());
        }
        catch (final RuntimeException e)
        {
            System.out.println("Unexpected runtime issue during spell: " + e.getMessage());
        }

        // Orc goes berserk on Elf - demonstrates unchecked exception handling
        try
        {
            orc.berserk(elf);
            System.out.println("Orc went berserk on Elf.");
        }
        catch (final LowRageException e)
        {
            System.out.println("Orc could not berserk: " + e.getMessage());
        }
        catch (final DamageException e)
        {
            System.out.println("Damage problem during berserk: " + e.getMessage());
        }
        catch (final RuntimeException e)
        {
            System.out.println("Unexpected runtime issue during berserk: " + e.getMessage());
        }

        // Test invalid healing - demonstrates unchecked HealingException handling
        try
        {
            elf.heal(-1);
        }
        catch (final HealingException e)
        {
            System.out.println("Healing error caught safely: " + e.getMessage());
        }

        System.out.println("\n=== Final Status After Fighting ===");
        System.out.println(dragon.getDetails());
        System.out.println(elf.getDetails());
        System.out.println(orc.getDetails());
    }
}
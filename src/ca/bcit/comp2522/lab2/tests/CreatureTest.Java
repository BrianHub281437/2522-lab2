package ca.bcit.comp2522.lab2;

import ca.bcit.comp2522.lab2.Creature;
import ca.bcit.comp2522.lab2.DamageException;
import ca.bcit.comp2522.lab2.Dragon;
import ca.bcit.comp2522.lab2.Elf;
import ca.bcit.comp2522.lab2.HealingException;
import ca.bcit.comp2522.lab2.LowFirePowerException;
import ca.bcit.comp2522.lab2.LowManaException;
import ca.bcit.comp2522.lab2.LowRageException;
import ca.bcit.comp2522.lab2.Orc;

import java.util.Date;

/**
 * Demonstrates substitution (polymorphism), instanceof, getClass(), and exception handling.
 *
 * @author YourName
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
     *
     * @param args command line args
     */
    public static void main(final String[] args)
    {
        final Creature c1;
        final Creature c2;
        final Creature c3;

        c1 = new Dragon("Smolder", new Date(), DRAGON_HEALTH, DRAGON_FIRE_POWER);
        c2 = new Elf("Elowen", new Date(), ELF_HEALTH, ELF_MANA);
        c3 = new Orc("Gruk", new Date(), ORC_HEALTH, ORC_RAGE);

        printDetailsPolymorphic(c1);
        printDetailsPolymorphic(c2);
        printDetailsPolymorphic(c3);

        showRuntimeTypes(c1);
        showRuntimeTypes(c2);
        showRuntimeTypes(c3);

        makeCreaturesFight(c1, c2, c3);
    }

    private static void printDetailsPolymorphic(final Creature creature)
    {
        System.out.println(creature.getDetails());
    }

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

        try
        {
            elf.heal(-1);
        }
        catch (final HealingException e)
        {
            System.out.println("Healing error caught safely: " + e.getMessage());
        }

        System.out.println("After fighting:");
        System.out.println(dragon.getDetails());
        System.out.println(elf.getDetails());
        System.out.println(orc.getDetails());
    }
}

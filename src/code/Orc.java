package code;

import java.util.Date;

public class Orc extends Creature {
    private static final int MAX_RAGE = 30;
    private static final int MIN_RAGE = 0;

    private static final int BERSERK_COST = 5;
    private static final int NORMAL_DAMAGE = 15;
    private static final int DOUBLE_DAMAGE = 30;
    private static final int RAGE_THRESHOLD = 20;

    private int rage;

    Orc(String name,
        Date dateOfBirth,
        int health,
        int rage) {
        super(name,
                dateOfBirth,
                health);

        if (rage < MIN_RAGE ||
                rage > MAX_RAGE ){
            throw new IllegalArgumentException("rage should be between 0-30");
        }
        this.rage = rage;
    }
    protected void berserk(final Creature target) {
        if (rage < BERSERK_COST) {
            throw new LowRageException("Not enough rage to go berserk");
        }
        rage += BERSERK_COST;
        if (rage > MAX_RAGE){
            rage = MAX_RAGE;
        }

        int damage;
        if (rage > RAGE_THRESHOLD) {
            damage = DOUBLE_DAMAGE;
        } else {
            damage = NORMAL_DAMAGE;
        }
        target.takeDamage(damage);
    }
    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Rage: " + rage);
    }


    }
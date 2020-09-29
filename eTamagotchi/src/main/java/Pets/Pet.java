package Pets;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Pet implements iPet, Serializable {
    private String name;
    private final boolean canBeBorn = true;
    private LocalDateTime timeDead;
    private LocalDateTime lastFeed;
    private int lifes = 3;
    private final static int petRadius = 55;


    private long age;
    private boolean hunger = false;

    public LocalDateTime getTimeDead() {
        return timeDead;
    }
    public void setTimeDead(LocalDateTime timeDead) {
        this.timeDead = timeDead; isCanBeBorn();
    }

    public boolean isHunger() {
        return hunger;
    }
    public void setHunger(boolean hunger) {
        this.hunger = hunger;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public boolean isCanBeBorn() {

        if (timeDead!=null && (ChronoUnit.MINUTES.between(timeDead, LocalDateTime.now()) < 5)) {
            return !canBeBorn;
        } else return canBeBorn;

    }
    public LocalDateTime getLastFeed() {
        if(lastFeed != null) {
            return lastFeed;
        }
        else lastFeed=LocalDateTime.now();
        return lastFeed;
    }

    public void setLastFeed(LocalDateTime lastFeed) {
        this.lastFeed = lastFeed;
    }


    public void setLifes(int lifes) {

        this.lifes = lifes;
    }

    public int getLifes() {
        return lifes;
    }

    public int getPetRadius() {
        return petRadius;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", canBeBorn=" + canBeBorn +
                ", timeDead=" + timeDead +
                ", lastFeed=" + lastFeed +
                ", lifes=" + lifes +
                ", age=" + age +
                ", hunger=" + hunger +
                '}';
    }
}

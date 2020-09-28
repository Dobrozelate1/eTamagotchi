package GamePets;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Pet implements iPet, Serializable {

    private String name;
    private final boolean canBeBorn = true;
    private LocalDateTime timeDead;
    private LocalDateTime lastFeed;
    private int lifes = 2;


    private long age;
    private final String heartPath = "pets/heart.png";
    private boolean hunger = false;

    public LocalDateTime getTimeDead() {
        return timeDead;
    }

    public boolean isHunger() {
        return hunger;
    }

    public void setTimeDead(LocalDateTime timeDead) {
        this.timeDead = timeDead;
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

    public String getHeartPath() {
        return heartPath;
    }

    public boolean isCanBeBorn() {
//        if () {
            if (ChronoUnit.MINUTES.between(timeDead, LocalDateTime.now()) < 5) {
                return !canBeBorn;
            } else return canBeBorn;
//        }
    }


//    public void setCanBeBorn(boolean canBeBorn) {
//        this.canBeBorn = canBeBorn;
//    }

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

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", canBeBorn=" + canBeBorn +
                ", timeDead=" + timeDead +
                ", lastFeed=" + lastFeed +
                ", lifes=" + lifes +
                ", age=" + age +
                ", heartPath='" + heartPath + '\'' +
                ", hunger=" + hunger +
                '}';
    }
}

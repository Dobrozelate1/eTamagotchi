package GamePets;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pet implements iPet, Serializable {

    private String name;
    private boolean canBeBorn = true;
    private LocalDateTime timeDead;
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
        return canBeBorn;
    }

    public void setCanBeBorn(boolean canBeBorn) {
        this.canBeBorn = canBeBorn;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", canBeBorn=" + canBeBorn +
                ", age=" + age +
                ", heartPath='" + heartPath + '\'' +
                '}';
    }
}

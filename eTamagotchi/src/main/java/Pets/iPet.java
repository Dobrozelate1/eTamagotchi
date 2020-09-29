package Pets;

import java.time.LocalDateTime;

public interface iPet {

    public LocalDateTime getTimeDead();
    public void setTimeDead(LocalDateTime timeDead);
    public boolean isHunger();

    public void setHunger(boolean hunger);

    public long getAge();

    public void setAge(long age);

    public void setName(String name);

    public String getName();

    public boolean isCanBeBorn();

//    public void setCanBeBorn(boolean canBeBorn) ;

    public LocalDateTime getLastFeed();

    public void setLastFeed(LocalDateTime lastFeed);

    public void setLifes(int lifes);

    public int getLifes();
    public int getPetRadius() ;


    default String getIdleImageOfPet() {
        return String.format(
                "pets/%s_idle.png", getClass().getSimpleName());
    }

    default String getRunImageOfPet(int i) {
        String petType = getClass().getSimpleName();
        return String.format(
                "pets/%s/%s_run" + i + ".png", petType, petType);
    }   default String getSpriteImageOfPet() {
        String petType = getClass().getSimpleName();
        return String.format(
                "pets/%s/%s_runs.png", petType, petType);
    }

    default String getFoodImageOfPet() {
        String petType = getClass().getSimpleName();
        return String.format(
                "pets/%s_feed.png", petType);
    }


}

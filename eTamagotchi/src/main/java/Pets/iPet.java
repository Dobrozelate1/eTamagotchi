package Pets;

import java.time.LocalDateTime;

public interface iPet {

    LocalDateTime getTimeDead();
     void setTimeDead(LocalDateTime timeDead);
    boolean isHunger();

     void setHunger(boolean hunger);

     long getAge();

     void setAge(long age);

    void setName(String name);

     String getName();

   boolean isCanBeBorn();

//    public void setCanBeBorn(boolean canBeBorn) ;

    LocalDateTime getLastFeed();

     void setLastFeed(LocalDateTime lastFeed);

     void setLifes(int lifes);

    int getLifes();
     int getPetRadius() ;


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

package GamePets;

import java.time.LocalDateTime;

public interface iPet {

    public LocalDateTime getTimeDead();

    public boolean isHunger() ;


    public void setTimeDead(LocalDateTime timeDead);

    public void setHunger(boolean hunger) ;

    public long getAge() ;

    public void setAge(long age) ;

    public void setName(String name) ;

    public String getName() ;

    public String getHeartPath() ;

    public boolean isCanBeBorn() ;

//    public void setCanBeBorn(boolean canBeBorn) ;

    public LocalDateTime getLastFeed() ;

    public void setLastFeed(LocalDateTime lastFeed) ;


    public void setLifes(int lifes) ;

    public int getLifes() ;

//    final static int amountOfHeart = 2;
//
//    public String getHeartPath();
//
//    public void setAge(long age);
//
//    public long getAge();
//
//    public boolean isCanBeBorn();

    default String getIdleImageOfPet() {
        return String.format(
                "pets/%s_idle.png", getClass().getSimpleName());
    }

    default String getSpriteImageOfPet(int i) {
        String petType = getClass().getSimpleName();
        return String.format(
                "pets/%s/%s_run" + i + ".png", petType, petType);
    }

    default String getFoodImageOfPet() {
        String petType = getClass().getSimpleName();
        return String.format(
                "pets/%s_feed.png", petType);
    }
}


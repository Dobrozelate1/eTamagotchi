package GamePets;

public interface iPet {

    final static int amountOfHeart = 3;

    public String getHeartPath();

    public void setAge(long age);

    public long getAge();

    public boolean isCanBeBorn();

    default String getIdleImageOfPet() {
        return String.format(
                "pets/%s_idle.png", getClass().getSimpleName());
    }

    default String getSpriteImageOfPet(int i) {
        String petType = getClass().getSimpleName();
        return String.format(
                "pets/%s/%s_run" + i + ".png", petType, petType);
    }

    default String getFoodImageOfPet(int i) {
        String petType = getClass().getSimpleName();
        return String.format(
                "pets/%s/%s_run" + i + ".png", petType, petType);
    }
}


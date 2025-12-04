package itmoLessons.kursovaya.fitness.base;

public enum SubscriptionType {
    ONE_DAY_PASS(true, true, false, 8, 22),
    EARLY_BIRD_PASS(false, false, true, 8, 16),
    FULL_PASS(true, true, true, 8, 22);

    private final boolean isSwimmingPoolInclude;
    private final boolean isGymInclude;
    private final boolean isGroupFitnessInclude;
    private final int startTime;
    private final int endTime;

    SubscriptionType(boolean isSwimmingPoolInclude,
                     boolean isGymInclude,
                     boolean isGroupFitnessInclude,
                     int startTime,
                     int endTime) {
        this.isSwimmingPoolInclude = isSwimmingPoolInclude;
        this.isGymInclude = isGymInclude;
        this.isGroupFitnessInclude = isGroupFitnessInclude;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    /**
     * Добавление клиентов в группу по фитнесу
     *
     * @return true
     */
    public boolean isGroupFitnessInclude() {
        return isGroupFitnessInclude;
    }


    /**
     * Добавление клиентов в группу c бассейном
     *
     * @return true
     */
    public boolean isSwimmingPoolInclude() {
        return isSwimmingPoolInclude;
    }

    /**
     * Добавление клиентов в группу c залом
     *
     * @return true
     */
    public boolean isGymInclude() {
        return isGymInclude;
    }

}



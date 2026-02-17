package itmoLessons.kursovaya.fitness;


import static itmoLessons.kursovaya.fitness.base.SubscriptionType.*;

public class Application {


    public static void main(String[] args) {
        Gym gym = new Gym();

        GymMembers petr = new GymMembers("Петр",
                "Анисимов", "1987-11-06",
                ONE_DAY_PASS );
        GymMembers alex = new GymMembers("Ларин",
                "Алексей", "1999-11-06",
                EARLY_BIRD_PASS);
        GymMembers andrey = new GymMembers("Андрей", "Иванов",
                "1984-11-06",
                FULL_PASS);

        //  petr.setExpireDateRegistration();

        //  gym.addToPool(alex);
    gym.addToPool(petr);
    gym.addToGym(petr);
        gym.printSwimmingPool();

        gym.printGroup();





    }
}
package itmoLessons.kursovaya.fitness;

import itmoLessons.kursovaya.fitness.base.SubscriptionValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Gym extends SubscriptionValidator {


    private static final Log log = LogFactory.getLog(Gym.class);
    private int swimmingPoolCount = 0;
    private int gymCount = 0;
    private int groupCount = 0;
    private final int size = 20;
    public GymMembers[] swimmingPoolArray = new GymMembers[size];
    public GymMembers[] gymArray = new GymMembers[size];
    public GymMembers[] groupArray = new GymMembers[size];

    /**
     * метод добавления клиентов в бассейн
     *
     * @param gymClients
     */
    public void addToPool(GymMembers gymClients) {
        log.info("пытаемся добавить значение" + gymClients.toString() + " в массив swimmingPoolArray");
        if (checkExpireDate(gymClients) && checkSwimmingPoolInclude(gymClients)) {
            if (swimmingPoolCount < swimmingPoolArray.length) {
                swimmingPoolArray[swimmingPoolCount] = gymClients;
                log.info("[SUCCESS] успешно добавили значение" + gymClients + " в массив swimmingPoolArray");
                swimmingPoolCount++;
            } else {
                System.out.println("Бассейн переполнен!");
            }
        }
    }


    /**
     * метод добавления клиентов в бассейн
     *
     * @param gymClients
     */
    public void addToGym(GymMembers gymClients) {
        log.info("пытаемся добавить значение" + gymClients.toString() + " в массив gymArray");
        if (checkExpireDate(gymClients) && checkGymInclude(gymClients)) {
            if (gymCount < gymArray.length) {
                gymArray[gymCount] = gymClients;
                log.info("[SUCCESS] успешно добавили значение" + gymClients + " в массив gymArray");
                gymCount++;
            } else {
                System.out.println("Зал переполнен!");
            }
        }
    }


    /**
     * метод добавления клиентов в бассейн
     *
     * @param gymClients
     */
    public void addToGroupTraining(GymMembers gymClients) {
        log.info("пытаемся добавить значение" + gymClients.toString() + " в массив groupArray");
        if (checkExpireDate(gymClients) &&
                checkGroupTrainingInclude(gymClients)) {
            if (groupCount < groupArray.length) {
                groupArray[groupCount] = gymClients;
                log.info("[SUCCESS] успешно добавили значение" + gymClients + " в массив groupArray");
                groupCount++;
            } else {
                System.out.println("Групповые занятия переполнены! ");
            }
        }
    }


    public void printGymMembers() {
        log.info("Выводим данные массива: gymArray ");
        System.out.println("Количество клиентов в зале: " + gymCount);
        for (GymMembers gymMembers : gymArray) {
            System.out.println(gymMembers);
        }
    }

    public void printSwimmingPool() {
        log.info("Выводим данные массива: swimmingPoolArray ");
        System.out.println("Количество клиентов в бассейне: " + swimmingPoolCount);
        for (GymMembers gymMembers : swimmingPoolArray) {
            System.out.println(gymMembers);
        }
    }

    public void printGroup() {
        log.info("Выводим данные массива: groupArray ");
        System.out.println("Количество клиентов в бассейне: " + groupCount);
        for (GymMembers gymMembers : swimmingPoolArray) {
            System.out.println(gymMembers);
        }
    }


}
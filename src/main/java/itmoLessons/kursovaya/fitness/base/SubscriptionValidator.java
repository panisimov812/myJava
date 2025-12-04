package itmoLessons.kursovaya.fitness.base;

import itmoLessons.kursovaya.fitness.Gym;
import itmoLessons.kursovaya.fitness.GymMembers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;

public abstract class SubscriptionValidator {
    private static final Log log = LogFactory.getLog(Gym.class);

    /**
     * метод проверки протухания абонимента
     *
     * @param gymClients
     * @return true
     */
    public boolean checkExpireDate(GymMembers gymClients) {
        log.info("Проверяем: " + gymClients.toString() + " на активность абонимента");
        if (gymClients.getDateOfExpireRegistration().isBefore(gymClients.getDateOfRegistration())) {
            System.out.println(gymClients.getFullName() + " к сожалению ваш абонимент просрочен");
            return false;
        }
        return true;
    }

    /**
     * метод проверки наличия бассейна в абонименте
     *
     * @param gymClients
     * @return true
     */
    public boolean checkSwimmingPoolInclude(GymMembers gymClients) {
        log.info("Проверяем: " + gymClients.toString() + " на доступность бассейна");
        if (gymClients.getSubscriptionType().isSwimmingPoolInclude()) return true;
        else {
            System.out.println("Уважаемый " + gymClients.getFullName() + " по вашему" +
                    " абонименту вы не можете посещать бассейн!");
            return false;
        }
    }

    /**
     * метод проверки наличия зала в абонименте
     *
     * @param gymClients
     * @return true
     */
    public boolean checkGymInclude(GymMembers gymClients) {
        log.info("Проверяем: " + gymClients.toString() + " на доступность спорт зала");
        if (gymClients.getSubscriptionType().isGymInclude()) return true;
        else {
            System.out.println("Уважаемый " + gymClients.getFullName() + " по вашему" +
                    " абонименту вы не можете посещать спорт зал!");
            return false;
        }
    }

    /**
     * метод проверки наличия зала в абонименте
     *
     * @param gymClients
     * @return true
     */
    public boolean checkGroupTrainingInclude(GymMembers gymClients) {
        log.info("Проверяем: " + gymClients.toString() + " на доступность групповых тренеровок");
        if (gymClients.getSubscriptionType().isGroupFitnessInclude()) return true;
        else {
            System.out.println("Уважаемый " + gymClients.getFullName() + " по вашему" +
                    " абонименту вы не можете посещать групповые тренировки!");
            return false;
        }
    }
}

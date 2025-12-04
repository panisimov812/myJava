package itmoLessons.kursovaya.fitness;

import itmoLessons.kursovaya.fitness.base.SubscriptionType;

import java.time.LocalDate;

import static java.time.LocalDate.now;

public class GymMembers extends Clients {
    private final LocalDate dateOfRegistration;
    private LocalDate dateOfExpireRegistration;

    public GymMembers(String lastName,
                      String name,
                      String dateOfBirthday,
                      SubscriptionType subscriptionType) {
        super(lastName, name, dateOfBirthday, subscriptionType);
        this.dateOfRegistration = now();
        this.dateOfExpireRegistration = now().plusMonths(6);
    }

    /**
     * Возвращает дату регистрации .now()
     * @return объект LocalDate
     */
    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public LocalDate getDateOfExpireRegistration() {
        return dateOfExpireRegistration;
    }

    /**
     * Метод для тестов, устанавливает просроченную дату
     */
    public void setExpireDateRegistration() {
        this.dateOfExpireRegistration =  now().minusYears(1);
    }

}

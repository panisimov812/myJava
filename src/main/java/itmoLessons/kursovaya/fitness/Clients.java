package itmoLessons.kursovaya.fitness;

import itmoLessons.kursovaya.fitness.base.SubscriptionType;

//нейминг
abstract class Clients {
    private String name;
    private String lastName;
    private String dateOfBirthday;

    private SubscriptionType subscriptionType;

    public Clients(String lastName,
                   String name,
                   String dateOfBirthday,
                   SubscriptionType subscriptionType) {
        setLastName(lastName);
        setName(name);
        setDateOfBirthday(dateOfBirthday);
        setSubscriptionType(subscriptionType);


    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }


    /**
     * задется форматом: год - месяц - число
     * <p>пример</p>
     * <b>"2025-05-14"</b>
     *
     * @param dateOfBirthday
     */
    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("Имя клиента не может быть пустым");
        }
        this.name = name;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new NullPointerException("Фамилия не может быть пустой");
        }
        this.lastName = lastName;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return name + " " + lastName;
    }


    @Override
    public String toString() {
        return "Clients{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                ", subscriptionType=" + subscriptionType +
                '}';
    }
}

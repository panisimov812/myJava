package itmoLessons.kursovaya.fitness;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import static itmoLessons.kursovaya.fitness.base.SubscriptionType.FULL_PASS;
import org.junit.jupiter.api.Test;



class GymMembersTest {
    private Gym gym;
    private GymMembers member;

    @BeforeEach
    void setup() {
        gym = new Gym();
        member = new GymMembers("Иван", "Иванов", "1984-11-06", FULL_PASS);
    }

    @Test
    void testAddToGymSuccessfully() {

        // WHEN — добавляем клиента
        gym.addToGym(member);

        // THEN — клиент добавлен
        Assertions.assertEquals(member, gym.gymArray[0],
                "Клиент должен быть добавлен в первый слот массива");

        // проверяем что счётчик увеличился
        Assertions.assertEquals(1, getGymCount(gym),
                "Счётчик клиентов в зале должен стать 1");

    }

    /**
     * Достаём значение закрытого поля gymCount через рефлексию,
     * потому что в классе нет геттера.
     */
    private int getGymCount(Gym gym) {
        try {
            var field = Gym.class.getDeclaredField("gymCount");
            field.setAccessible(true);
            return field.getInt(gym);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



package itmoLessons.lesson15Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tasks {

    public static void main(String[] args) {
        User userPetr = new User("Петр", "pass1", Role.ADMIN, 28);
        User userLesha = new User("Леха", "pass2", Role.USER, 28);
        User userGalya = new User("Галя", "pass3", Role.USER, 28);
        User userIra = new User("Ира", "pass4", Role.ADMIN, 28);

        ArrayList<User> arrayListEx = new ArrayList<>();
        HashMap<String, User> hashMapEx = new HashMap<>();
        ArrayList<String> arrayListEx2 = new ArrayList<>();

        hashMapEx.put(userPetr.getLogin(), userPetr);
        hashMapEx.put(userPetr.getLogin(), userLesha);
        hashMapEx.put(userPetr.getLogin(), userGalya);
        hashMapEx.put(userPetr.getLogin(), userIra);
        arrayListEx.add(userPetr);
        arrayListEx.add(userLesha);
        arrayListEx.add(userGalya);
        arrayListEx.add(userIra);

        // заполняем мапу login → user
        for (User user : arrayListEx) {
            hashMapEx.put(user.getLogin(), user);
        }

        System.out.println(hashMapEx); //{Ира=User{login='Ира', password='pass4',...


        for (Map.Entry<String, User> pair : hashMapEx.entrySet()) {
            if (pair.getValue().getAge() > 20) {
                arrayListEx2.add(pair.getKey());
            }
            if (pair.getValue().getRole() != Role.ADMIN) {
                System.out.println("пользователи без  роли админ: " + pair.getValue().getLogin());
            }
        }
        System.out.println("Список ребят кому за 20: " + arrayListEx2); //Список ребят кому за 20: [Ира, Петр, Леха, Галя]


        //на основе сущестующей мапы создать новую
        //где ключи- возраст пользователей
        //значение - списки пользователей соответстуюего возроста
        HashMap<Integer, ArrayList<User>> ageMap = new HashMap<>();

        for (User user : hashMapEx.values()) { // перебираем всех пользователей
           if (!ageMap.containsKey(user.getAge())) {
               //создаем пустой список
               ArrayList<User> users = new ArrayList<>();
               users.add(user);
               ageMap.put(user.getAge(), users);
           }
        }


    }
}

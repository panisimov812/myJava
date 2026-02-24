package itmoLessons.lesson15Map;

import java.util.HashMap;
import java.util.Map;

public class MapLesson {
    //MAP хранят данные в паре ключ-значение
    //наиболее популярные HashMap<k,v>
    //Особенности HashMap:
    // 1. Хранит ключи в hash таблицах(на основе hash кода)
    // 2. Для ключей должны бытть переопределены методы hashCode() и equals()

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        //добавление элементов
        hashMap.put("Москва", 790);
        hashMap.put("Ростов", 270);
        hashMap.put("Великий Новгород", 75);
        hashMap.put("Тверь", 12);
        hashMap.put(null, null);

        //удаление пары
        hashMap.remove(null); //по ключу - удалиться пара целиком
        hashMap.remove("Москва", 5); //по ключу и значению (но такой пары нет, значение не то) поэтому не удалиться
        hashMap.remove("Ростов", 270);
        System.out.println(hashMap);

        //замена
        hashMap.replace("Великий Новгород", 100); //по ключу установил новое значение
        hashMap.replace("Тверь", 12, 23);//заменит если найдет ключ и значение которые были ранее
        System.out.println(hashMap);

        //получение
        System.out.println(hashMap.get("Тверь")); //если не найдет ключ то вернет null
        System.out.println(hashMap.get("Москва"));
        System.out.println(hashMap.getOrDefault("Санкт-Петербург", 0));//если в мапе не найдет ключ то вернет значение по умолчанию
        System.out.println(hashMap.getOrDefault("Великий Новгород", 0));//если в мапе не найдет ключ то вернет значение по умолчанию

        //провереить, содержится ли в мапе ключ
        System.out.println(hashMap.containsKey("Санкт-Петербург")); //вернет  false если ключа нет
        //проверит, содержит ли в мапе значение
        System.out.println(hashMap.containsValue(10000));//вернет  false если значения нет

        System.out.println("-- перебор мап---");

        //на каждой итерации в pair будет и ключ и значение
        //hashMap.keySet() - только ключи (Set)
        //hashMap.getValues() - только значения (Collection)
        for (Map.Entry<String, Integer> pair : hashMap.entrySet()) { //entrySet() это список пар
            System.out.println("город (ключ): " + pair.getKey());
            System.out.println("количество покупателей (значение): " + pair.getValue());
        }


    }


}

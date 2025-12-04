package itmoLessons.lesson11Enum.enums;

import java.time.LocalDateTime;


public class Article {
    private String title;
    public String text;
    private LocalDateTime created;
    //ссылка на страну
    private Country country;

    public Article(String title){
        this.title = title;
        //метод статический когда имя класса точка метод
        //метод now() создаст экземпляр типа LocalDateTime и сохранит туда значение
        //текущих дат и времяни
        created = LocalDateTime.now();
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}

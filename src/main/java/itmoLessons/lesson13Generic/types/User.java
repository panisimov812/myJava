package itmoLessons.lesson13Generic.types;

//В классе собираемся использовать некий тип дженерик тип, либо один либо несколько
public class User<T> {

    //свойство id будет типа T
    private T id; /* внутри классы у свойства id можно вызвать только методы Object*/
    private String login;

    public void setId(T id){
        this.id = id;
    }

    //наш геттер возвращает тип T
    public T getId(){
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}

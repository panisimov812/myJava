package itmoLessons.lesson6.homeWork.mountainsTask;

public class Climber {

    private String name;
    private String address;

    public Climber() {
    }

    public Climber(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть null или меньше 3 букв");
        }
        this.name = name;
    }

    public void setAddress(String address) {
        if (address == null || address.length() < 4) {
            throw new IllegalArgumentException("Адресс не может быть null или меньше 4 букв");
        }
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getFullInfo() {
        return "Имя:" + name + " " + "Адрес: " + address;
    }


}

package itmoLessons.lesson11Enum.enums;

public enum Planet {
    MARS(12, 123, "Марс"),
    SATURN(13, 15, "Сатурн"),
    EARTH(14, 135, "Земля");
    private int mass;
    private int radius;
    private final String name;

    Planet(int mass, int radius, String name) {
        this.mass = mass;
        this.radius = radius;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}

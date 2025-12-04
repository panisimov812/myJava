package itmoLessons.lesson6.homeWork.mountainsTask;

import java.util.Objects;

public class Mountain {
    private String nameOfMountain;
    private String landOfMountain;
    private int highOfMountain;

    public Mountain(){}
    public Mountain(String nameOfMountain,
                    String landOfMountain,
                    int highOfMountain) {
        this.nameOfMountain = nameOfMountain;
        this.landOfMountain = landOfMountain;
        this.highOfMountain = highOfMountain;
    }

    public void setNameOfMountain(String nameOfMountain) {
        if (nameOfMountain == null || nameOfMountain.length() <= 4) {
            throw new IllegalArgumentException("Вы не ввели название горы");
        }
        this.nameOfMountain = nameOfMountain;
    }

    public void setLandOfMountain(String landOfMountain) {
        if (landOfMountain == null || landOfMountain.length() < 4) {
            throw new IllegalArgumentException("Страна горы не может быть null или" +
                    " иметь меньше четерех символов");
        }
        this.landOfMountain = landOfMountain;
    }

    public void setHighOfMountain(int highOfMountain) {
        if (highOfMountain < 100) {
            throw new IllegalArgumentException("Гора не может быть " +
                    "ниже 100 метров");
        }
        this.highOfMountain = highOfMountain;
    }

    public String getLandOfMountain() {
        return landOfMountain;
    }

    public String getNameOfMountain() {
        return nameOfMountain;
    }


    @Override
    public String toString() {
        return "Mountain{" +
                "nameOfMountain='" + nameOfMountain + '\'' +
                ", landOfMountain='" + landOfMountain + '\'' +
                ", highOfMountain=" + highOfMountain +
                '}';
    }
}

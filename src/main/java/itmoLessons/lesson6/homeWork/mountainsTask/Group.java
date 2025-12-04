package itmoLessons.lesson6.homeWork.mountainsTask;


import java.util.Arrays;
import java.util.Objects;


public class Group {
    private Mountain mountain;
    private boolean isGroupAvailable;
    private Climber[] climbers = new Climber[5];

    public Group(Mountain mountain) {
        Objects.requireNonNull(mountain, "не может быть null");
        this.mountain = mountain;
    }

    public Group(boolean isGroupAvailableValue, Mountain mountain) {
        this(mountain);
        this.isGroupAvailable = isGroupAvailableValue;
    }

    public void addClimberToGroup(Climber climber) {
        if (isGroupAvailable) {
            for (int i = 0; i < climbers.length; i += 1) {
                if (climbers[i] == null) {
                    climbers[i] = climber;
                    break;
                }
            }
        }
    }


    void isGroupUnAvailable(boolean value) {
        isGroupAvailable = value;
    }


    @Override
    public String toString() {
        return "Group{" +
                "mountain=" + mountain +
                ", isGroupAvailable=" + isGroupAvailable +
                ", climbers=" + Arrays.toString(climbers) +
                '}';
    }
}

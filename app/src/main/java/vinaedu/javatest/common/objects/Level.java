package vinaedu.javatest.common.objects;

/**
 * Created by bscenter on 11/08/2016
 */
public enum Level {
    easy(1), medium(2), difficult(3);

    private final int value;

    Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Level getType(int value) {
        if (value == 1) {
            return easy;
        } else if (value == 2) {
            return medium;
        } else if (value == 3) {
            return difficult;
        }
        return null;
    }
}

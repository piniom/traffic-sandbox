package pl.tcs.po.models.blocks;

import java.util.List;
import java.util.Random;

public enum Rotation {
    NORTH(0, "up"),
    EAST(90, "right"),
    SOUTH(180, "down"),
    WEST(270, "left");

    public final double degrees;
    public final int index;
    public final String alias;

    Rotation(double degrees, String alias){
        this.degrees = degrees;
        this.index = (int) (degrees/90);
        this.alias = alias;
    }

    private static final List<Rotation> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Rotation getRandomRotation(){
        //return NORTH;
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

package pl.tcs.po.models.blocks;

import java.util.List;
import java.util.Objects;
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

    public static Rotation getNextRotation(Rotation rotation) {
        if(rotation == NORTH) {
            return EAST;
        }
        if(rotation == EAST) {
            return SOUTH;
        }
        if(rotation == SOUTH) {
            return WEST;
        }
        return NORTH;
    }

    public static Rotation fromString(String s) throws IllegalArgumentException{
        if(Objects.equals(s, "EAST")) return EAST;
        if(Objects.equals(s, "WEST")) return WEST;
        if(Objects.equals(s, "SOUTH")) return SOUTH;
        if(Objects.equals(s, "NORTH")) return NORTH;
        throw new IllegalArgumentException();
    }

    double radians(){
        return Math.toRadians(degrees);
    }
}

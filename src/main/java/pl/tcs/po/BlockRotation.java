package pl.tcs.po;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BlockRotation {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    public final double degrees;

    BlockRotation(double degrees){
        this.degrees = degrees;
    }

    private static final List<BlockRotation> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static BlockRotation getRandomRotation(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

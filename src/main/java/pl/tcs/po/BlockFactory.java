package pl.tcs.po;

import java.util.Random;

public class BlockFactory {
    private static final Random RANDOM = new Random();

    public static Block getRandomBlock(){
        switch (RANDOM.nextInt(2)){
            case 0:
                return new StraightBlock(BlockRotation.getRandomRotation());
            default:
                return new EmptyBlock();
        }
    }
}

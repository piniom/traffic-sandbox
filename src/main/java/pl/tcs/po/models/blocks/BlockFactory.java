package pl.tcs.po.models.blocks;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BlockFactory {
    public static Block getRotated(Block blockToRotate) {
        Rotation nextRotation = Rotation.getNextRotation(blockToRotate.getRotation());

        switch(blockToRotate.getName()) {
            case "straight":
                return new StraightBlock(blockToRotate.getPosition(), nextRotation);
            case "curve":
                return new CurveBlock(blockToRotate.getPosition(), nextRotation);
            case "Tjunction":
                return new TJunctionBlock(blockToRotate.getPosition(), nextRotation);
            case "junction":
                return new JunctionBlock(blockToRotate.getPosition(), nextRotation);
            case "roundabout":
                return new RoundaboutBlock(blockToRotate.getPosition(), nextRotation);
            case "end":
                return new EndBlock(blockToRotate.getPosition(), nextRotation);
            default:
                return new EmptyBlock(blockToRotate.getPosition());
        }
    }
}

package pl.tcs.po.models.blocks;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BlockFactory {
    public static Block getRotated(Block blockToRotate) {
        Rotation nextRotation = Rotation.getNextRotation(blockToRotate.getRotation());

        return switch (blockToRotate.getName()) {
            case "straight" -> new StraightBlock(blockToRotate.getPosition(), nextRotation);
            case "curve" -> new CurveBlock(blockToRotate.getPosition(), nextRotation);
            case "Tjunction" -> new TJunctionBlock(blockToRotate.getPosition(), nextRotation);
            case "junction" -> new JunctionBlock(blockToRotate.getPosition(),nextRotation);
            case "roundabout" -> new RoundaboutBlock(blockToRotate.getPosition(), nextRotation);
            case "end" -> new EndBlock(blockToRotate.getPosition(), nextRotation);
            case "source" -> new SourceBlock(blockToRotate.getPosition(), nextRotation);
            case "destination" -> new DestinationBlock(blockToRotate.getPosition(), nextRotation);
            default -> new EmptyBlock(blockToRotate.getPosition());
        };
    }

    public static Block getFaded(Block blockToFade) {
        Rotation rotation = blockToFade.getRotation();

        return switch (blockToFade.getName()) {
            case "straight" -> new FadedStraightBlock(rotation);
            case "curve" -> new FadedCurveBlock(rotation);
            case "Tjunction" -> new FadedTJunctionBlock(rotation);
            case "junction" -> new FadedJunctionBlock(rotation);
            case "roundabout" -> new FadedRoundaboutBlock(rotation);
            case "end" -> new FadedEndBlock(rotation);
            case "source" -> new FadedSourceBlock(rotation);
            case "destination" -> new FadedDestinationBlock(rotation);
            default -> new FadedEmptyBlock();
        };
    }
}

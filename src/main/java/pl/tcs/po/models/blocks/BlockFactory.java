package pl.tcs.po.models.blocks;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BlockFactory {
    public static Block getRotated(Block blockToRotate) {
        Rotation nextRotation = Rotation.getNextRotation(blockToRotate.getRotation());

        return switch (blockToRotate.getName()) {
            case "straight" -> new StraightBlock(nextRotation);
            case "curve" -> new CurveBlock(nextRotation);
            case "Tjunction" -> new TJunctionBlock(nextRotation);
            case "junction" -> new JunctionBlock(nextRotation);
            case "roundabout" -> new RoundaboutBlock(nextRotation);
            case "end" -> new EndBlock(nextRotation);
            default -> new EmptyBlock();
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
            default -> new FadedEmptyBlock();
        };
    }
}

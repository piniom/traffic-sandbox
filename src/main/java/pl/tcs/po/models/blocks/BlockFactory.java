package pl.tcs.po.models.blocks;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BlockFactory {
    private static final Random RANDOM = new Random();

    public static Block getRandomBlock(){
        return switch (RANDOM.nextInt(13)) {
            case 0,1,8 -> new StraightBlock(Rotation.getRandomRotation());
            case 2,3,9 -> new CurveBlock(Rotation.getRandomRotation());
            case 10, 11 -> new EndBlock(Rotation.getRandomRotation());
            case 4,5 -> new TJunctionBlock(Rotation.getRandomRotation());
            case 6 -> new JunctionBlock(Rotation.getRandomRotation());
            case 7 -> new RoundaboutBlock(Rotation.getRandomRotation());
            default -> new EmptyBlock();
        };
    }

    public static HashSet<Block> getAllBlocks(){
        HashSet<Block> out = new HashSet<>();
        for(Rotation r : Rotation.values()){
            out.addAll(List.of(
                    new StraightBlock(r),
                    new CurveBlock(r),
                    new EndBlock(r),
                    new TJunctionBlock(r),
                    new JunctionBlock(r),
                    new RoundaboutBlock(r)
                    ));
        }
        out.add(new EmptyBlock());
        return out;
    }

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

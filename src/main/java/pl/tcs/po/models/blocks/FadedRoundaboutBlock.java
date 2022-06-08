package pl.tcs.po.models.blocks;

public class FadedRoundaboutBlock extends AbstractBlock {
    public FadedRoundaboutBlock(Rotation rotation) {
        super(rotation);
        for(Rotation r : Rotation.values())setBiLink(r, true);
    }

    @Override
    public String getName() {
        return "faded_roundabout";
    }
}

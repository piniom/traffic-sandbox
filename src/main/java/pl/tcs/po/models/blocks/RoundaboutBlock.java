package pl.tcs.po.models.blocks;

public class RoundaboutBlock extends AbstractBlock{

    public RoundaboutBlock(Rotation rotation) {
        super(rotation);
        for(Rotation r : Rotation.values())setBiLink(r, true);
    }

    @Override
    public String getName() {
        return "roundabout";
    }
}

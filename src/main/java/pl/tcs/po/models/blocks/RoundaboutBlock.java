package pl.tcs.po.models.blocks;

import pl.tcs.po.models.mobile.Vector2;

public class RoundaboutBlock extends AbstractBlock{

    public RoundaboutBlock(Vector2 position, Rotation rotation) {
        super(position, rotation);
        for(Rotation r : Rotation.values())setBiLink(r, true);
    }

    @Override
    public String getName() {
        return "roundabout";
    }
}

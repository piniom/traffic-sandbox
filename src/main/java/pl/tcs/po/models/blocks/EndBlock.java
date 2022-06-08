package pl.tcs.po.models.blocks;

import pl.tcs.po.models.mobile.Vector2;

public class EndBlock extends AbstractBlock{

    public EndBlock(Vector2 position, Rotation rotation) {
        super(position, rotation);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "end";
    }
}

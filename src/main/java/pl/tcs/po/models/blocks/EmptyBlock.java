package pl.tcs.po.models.blocks;

import pl.tcs.po.models.mobile.Vector2;

public class EmptyBlock extends AbstractBlock{
    public EmptyBlock(Vector2 position){this(position, Rotation.NORTH);}
    public EmptyBlock(Vector2 position, Rotation rotation) {
        super(position, rotation);
    }

    @Override
    public String getName() {
        return "empty";
    }
}


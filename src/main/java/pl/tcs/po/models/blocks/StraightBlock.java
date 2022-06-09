package pl.tcs.po.models.blocks;

import pl.tcs.po.models.mobile.Vector2;

public class StraightBlock extends AbstractBlock {

    public StraightBlock(Vector2 position, Rotation rotation) {
        super(position, rotation);
        setBiLink(Rotation.NORTH, true);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "straight";
    }

    @Override
    protected boolean checkPathEndpoints(int startId, int endId){
        if(startId == endId) return false;
        return super.checkPathEndpoints(startId, endId);
    }
}



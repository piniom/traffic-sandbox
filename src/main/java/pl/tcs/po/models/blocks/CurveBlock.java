package pl.tcs.po.models.blocks;

import pl.tcs.po.models.mobile.Vector2;

public class CurveBlock extends AbstractBlock{

    public CurveBlock(Vector2 position, Rotation rotation) {
        super(position, rotation);
        setBiLink(Rotation.SOUTH, true);
        setBiLink(Rotation.EAST, true);
    }

    @Override
    public String getName() {
        return "curve";
    }

    @Override
    protected boolean wrongPathEndpoints(int startId, int endId){
        if(startId == endId) return true;
        return super.wrongPathEndpoints(startId, endId);
    }
}

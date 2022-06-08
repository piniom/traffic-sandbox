package pl.tcs.po.models.blocks;

import pl.tcs.po.models.mobile.Vector2;

public class TJunctionBlock extends AbstractBlock{

    public TJunctionBlock(Vector2 position, Rotation rotation) {
        super(position, rotation);
        setBiLink(Rotation.NORTH, true);
        setBiLink(Rotation.EAST, true);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "Tjunction";
    }

    @Override
    protected boolean wrongPathEndpoints(int startId, int endId){
        if(startId == endId) return true;
        return super.wrongPathEndpoints(startId, endId);
    }
}

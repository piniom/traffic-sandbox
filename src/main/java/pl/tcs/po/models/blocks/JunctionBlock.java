package pl.tcs.po.models.blocks;

import pl.tcs.po.models.mobile.Vector2;

public class JunctionBlock extends AbstractBlock{
    public JunctionBlock(Vector2 position, Rotation rotation) {
        super(position, rotation);
        for(Rotation r : Rotation.values())setBiLink(r, true);
    }

    @Override
    public String getName() {
        return "junction";
    }

    @Override
    protected boolean checkPathEndpoints(int startId, int endId){
        if(startId == endId) return false;
        return super.checkPathEndpoints(startId, endId);
    }
}

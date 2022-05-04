package pl.tcs.po.models.blocks;

import javafx.scene.shape.Polyline;

public class StraightBlock extends AbstractBlock {

    public StraightBlock(Rotation rotation) {
        super(rotation);
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



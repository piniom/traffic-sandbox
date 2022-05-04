package pl.tcs.po.models.blocks;

public class CurveBlock extends AbstractBlock{

    public CurveBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.SOUTH, true);
        setBiLink(Rotation.EAST, true);
    }

    @Override
    public String getName() {
        return "curve";
    }

    @Override
    protected boolean checkPathEndpoints(int startId, int endId){
        if(startId == endId) return false;
        return super.checkPathEndpoints(startId, endId);
    }
}

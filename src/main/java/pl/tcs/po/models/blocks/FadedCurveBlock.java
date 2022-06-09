package pl.tcs.po.models.blocks;

public class FadedCurveBlock extends AbstractBlock{
    public FadedCurveBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.SOUTH, true);
        setBiLink(Rotation.EAST, true);
    }

    @Override
    public String getName() {
        return "faded_curve";
    }

    @Override
    protected boolean checkPathEndpoints(int startId, int endId){
        if(startId == endId) return true;
        return super.checkPathEndpoints(startId, endId);
    }
}

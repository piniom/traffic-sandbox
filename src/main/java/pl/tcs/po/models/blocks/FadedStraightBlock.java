package pl.tcs.po.models.blocks;

public class FadedStraightBlock extends AbstractBlock {
    public FadedStraightBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.NORTH, true);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "faded_straight";
    }

    @Override
    protected boolean checkPathEndpoints(int startId, int endId){
        if(startId == endId) return false;
        return super.checkPathEndpoints(startId, endId);
    }
}

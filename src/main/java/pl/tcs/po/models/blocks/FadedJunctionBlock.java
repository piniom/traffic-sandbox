package pl.tcs.po.models.blocks;

public class FadedJunctionBlock extends AbstractBlock {
    public FadedJunctionBlock(Rotation rotation) {
        super(rotation);
        for(Rotation r : Rotation.values())setBiLink(r, true);
    }

    @Override
    public String getName() {
        return "faded_junction";
    }

    @Override
    protected boolean checkPathEndpoints(int startId, int endId){
        if(startId == endId) return false;
        return super.checkPathEndpoints(startId, endId);
    }
}

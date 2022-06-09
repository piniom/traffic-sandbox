package pl.tcs.po.models.blocks;

public class FadedTJunctionBlock extends AbstractBlock{
    public FadedTJunctionBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.NORTH, true);
        setBiLink(Rotation.EAST, true);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "faded_Tjunction";
    }

    @Override
    protected boolean checkPathEndpoints(int startId, int endId){
        if(startId == endId) return true;
        return super.checkPathEndpoints(startId, endId);
    }
}

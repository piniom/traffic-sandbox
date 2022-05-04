package pl.tcs.po.models.blocks;

public class TJunctionBlock extends AbstractBlock{

    public TJunctionBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.NORTH, true);
        setBiLink(Rotation.EAST, true);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "Tjunction";
    }

    @Override
    protected boolean checkPathEndpoints(int startId, int endId){
        if(startId == endId) return false;
        return super.checkPathEndpoints(startId, endId);
    }
}

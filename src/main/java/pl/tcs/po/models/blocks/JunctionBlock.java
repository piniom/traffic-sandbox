package pl.tcs.po.models.blocks;

public class JunctionBlock extends AbstractBlock{
    public JunctionBlock(Rotation rotation) {
        super(rotation);
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

package pl.tcs.po.models.blocks;

public class EndBlock extends AbstractBlock{

    public EndBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "end";
    }
}

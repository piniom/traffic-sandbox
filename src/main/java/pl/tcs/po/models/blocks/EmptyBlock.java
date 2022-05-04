package pl.tcs.po.models.blocks;

public class EmptyBlock extends AbstractBlock{
    public EmptyBlock() {
        super(Rotation.NORTH);
    }

    @Override
    public String getName() {
        return "empty";
    }
}


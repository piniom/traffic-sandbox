package pl.tcs.po.models.blocks;

public class BlockConnection {
    public final Block target;
    public final Block source;
    public final int targetIndex;
    public final int sourceIndex;

    public BlockConnection(Block source, int sourceIndex, Block target, int targetIndex) {
        this.target = target;
        this.targetIndex = targetIndex;
        this.source = source;
        this.sourceIndex = sourceIndex;
    }

    public BlockConnection reversed(){
        return new BlockConnection(target, targetIndex, source, sourceIndex);
    }

    @Override
    public String toString(){
        return source.getName() + sourceIndex + " | " + target.getName() + targetIndex;
    }
}

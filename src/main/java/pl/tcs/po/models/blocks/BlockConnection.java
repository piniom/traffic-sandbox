package pl.tcs.po.models.blocks;

public class BlockConnection {
    public final Block target;
    public final int targetIndex;

    public BlockConnection(Block target, int targetIndex) {
        this.target = target;
        this.targetIndex = targetIndex;
    }
}

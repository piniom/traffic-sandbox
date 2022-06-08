package pl.tcs.po.models.blocks;

public class FadedEndBlock extends AbstractBlock {
    public FadedEndBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "faded_end";
    }
}

package pl.tcs.po.models.blocks;

public class FadedDestinationBlock extends AbstractBlock{
    public FadedDestinationBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "faded_destination";
    }
}

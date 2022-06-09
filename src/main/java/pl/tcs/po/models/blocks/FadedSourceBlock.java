package pl.tcs.po.models.blocks;

public class FadedSourceBlock extends AbstractBlock{
    public FadedSourceBlock(Rotation rotation) {
        super(rotation);
        setBiLink(Rotation.SOUTH, true);
    }

    @Override
    public String getName() {
        return "faded_source";
    }
}

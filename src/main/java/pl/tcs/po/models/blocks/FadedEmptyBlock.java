package pl.tcs.po.models.blocks;

public class FadedEmptyBlock extends AbstractBlock{
    public FadedEmptyBlock() {
        super(Rotation.NORTH);
    }

    @Override
    public String getName() {
        return "faded_empty";
    }
}

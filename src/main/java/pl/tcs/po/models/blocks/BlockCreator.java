package pl.tcs.po.models.blocks;


public enum BlockCreator {
    STRAIGHT() {
        @Override
        public Block getNewBlock(Rotation rotation) {
            return new StraightBlock(rotation);
        }
    },
    CURVE() {
        @Override
        public Block getNewBlock(Rotation rotation) {
            return new CurveBlock(rotation);
        }
    },
    TJUNCTION() {
        @Override
        public Block getNewBlock(Rotation rotation) {
            return new TJunctionBlock(rotation);
        }
    },
    JUNCTION(){
        @Override
        public Block getNewBlock(Rotation rotation) {
            return new JunctionBlock(rotation);
        }
    },
    ROUNDABOUT(){
        @Override
        public Block getNewBlock(Rotation rotation) {
            return new RoundaboutBlock(rotation);
        }
    },
    END(){
        @Override
        public Block getNewBlock(Rotation rotation) {
            return new EndBlock(rotation);
        }
    },
    EMPTY()
    ;


    BlockCreator() {}

    public Block getNewBlock(Rotation rotation) {
        return new EmptyBlock();
    }
}

package pl.tcs.po.models.blocks;


import pl.tcs.po.models.mobile.Vector2;

public enum BlockCreator {
    STRAIGHT() {
        @Override
        public Block getNewBlock(Vector2 position, Rotation rotation) {
            return new StraightBlock(position, rotation);
        }
    },
    CURVE() {
        @Override
        public Block getNewBlock(Vector2 position, Rotation rotation) {
            return new CurveBlock(position, rotation);
        }
    },
    TJUNCTION() {
        @Override
        public Block getNewBlock(Vector2 position, Rotation rotation) {
            return new TJunctionBlock(position, rotation);
        }
    },
    JUNCTION(){
        @Override
        public Block getNewBlock(Vector2 position, Rotation rotation) {
            return new JunctionBlock(position, rotation);
        }
    },
    ROUNDABOUT(){
        @Override
        public Block getNewBlock(Vector2 position, Rotation rotation) {
            return new RoundaboutBlock(position, rotation);
        }
    },
    END(){
        @Override
        public Block getNewBlock(Vector2 position, Rotation rotation) {
            return new EndBlock(position, rotation);
        }
    },
    EMPTY()
    ;


    BlockCreator() {}

    public Block getNewBlock(Vector2 position, Rotation rotation) {
        return new EmptyBlock(position, rotation);
    }
}

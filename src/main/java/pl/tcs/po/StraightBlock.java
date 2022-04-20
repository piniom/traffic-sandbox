package pl.tcs.po;

import java.util.ArrayList;
import java.util.List;

public class StraightBlock implements Block{

    private final BlockView view;
    private final BlockRotation rotation;
    private ArrayList<BlockConnection> connections = new ArrayList<>(2);

    public StraightBlock(BlockRotation rotation){
        this.rotation = rotation;
        this.view = new AbstractBlockView(this, rotation) {
            @Override
            String getImgPath() {
                return "File:img/straight.png";
            }
        };
    }

    @Override
    public ArrayList<BlockConnection> getConnections() {
        return connections;
    }

    @Override
    public BlockView getView() {
        return view;
    }
}



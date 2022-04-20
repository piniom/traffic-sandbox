package pl.tcs.po;

import java.util.ArrayList;

public class EmptyBlock implements Block{

    private final BlockView view = new AbstractBlockView(this) {
        @Override
        String getImgPath() {return "File:img/empty.png";}
    };

    @Override
    public ArrayList<BlockConnection> getConnections() {
        return new ArrayList<>();
    }

    @Override
    public BlockView getView() {
        return view;
    }
}


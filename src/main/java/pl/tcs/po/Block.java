package pl.tcs.po;

import javafx.scene.Node;

import java.util.ArrayList;

public interface Block {
    ArrayList<BlockConnection> getConnections();
    BlockView getView();
}

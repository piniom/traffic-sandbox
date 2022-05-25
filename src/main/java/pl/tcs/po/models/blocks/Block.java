package pl.tcs.po.models.blocks;

import javafx.scene.shape.Polyline;

import java.util.ArrayList;

public interface Block {
    ArrayList<BlockConnection> getOutConnections();
    ArrayList<BlockConnection> getInConnections();
    default BlockConnection getOutConnection(int index){
        return getOutConnections().get(index);
    }
    default BlockConnection getInConnection(int index){
        return getInConnections().get(index);
    }

    ArrayList<Boolean> getOutLinks();
    ArrayList<Boolean> getInLinks();
    default boolean getOutLink(int index){
        return getOutLinks().get(index);
    }
    default boolean getInLink(int index){
        return getInLinks().get(index);
    }
    String getName();
    Rotation getRotation();
    boolean setOutConnection(BlockConnection connection);
    boolean setInConnection(BlockConnection connection);

    Polyline getPath(int startId, int endId);
}

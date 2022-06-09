package pl.tcs.po.models.blocks;

import javafx.scene.shape.Polyline;
import pl.tcs.po.models.mobile.PointPath;
import pl.tcs.po.models.mobile.Vector2;
import pl.tcs.po.models.mobile.VectorRadial;
import pl.tcs.po.models.mobile.Vehicle;

import java.util.ArrayList;
import java.util.Collection;

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

    PointPath getPath(int startId, int endId);

    void update(long deltaTime);
    Collection<Vehicle> getVehicles();

    static Vector2 getDimensions(){
        return new Vector2(100, 100);
    }

    Vector2 getPosition();

    default Vector2 localToGlobalRotation(Vector2 point){
        return point.rotateAround(Block.getDimensions().scale(.5), getRotation().radians());
    }

    default Vector2 localToGlobal(Vector2 point){
        var rotated = localToGlobalRotation(point);
        return rotated.add(getPosition());
    }

    default Vehicle newVehicle(Vector2 localPoint, double radians){
        return new Vehicle(localToGlobal(localPoint), new VectorRadial(0, radians + getRotation().radians()), null);
    }
}

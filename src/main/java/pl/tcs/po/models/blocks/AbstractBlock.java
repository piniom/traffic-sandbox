package pl.tcs.po.models.blocks;

import javafx.scene.shape.Polyline;
import pl.tcs.po.models.mobile.Vector2;
import pl.tcs.po.models.mobile.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractBlock implements Block {

    private static int SIDES_COUNT = 4;

    private Rotation rotation;
    private final Vector2 position;
    protected ArrayList<BlockConnection> outConnections;
    protected ArrayList<BlockConnection> inConnections;
    protected ArrayList<Boolean> outLinks;
    protected ArrayList<Boolean> inLinks;

    public AbstractBlock(Vector2 position, Rotation rotation){
        this.position = position;
        this.rotation = rotation;
        this.outConnections = new ArrayList<>(Collections.nCopies(SIDES_COUNT, null));
        this.inConnections = new ArrayList<>(Collections.nCopies(SIDES_COUNT, null));
        this.outLinks = new ArrayList<>(Collections.nCopies(SIDES_COUNT, false));
        this.inLinks = new ArrayList<>(Collections.nCopies(SIDES_COUNT, false));
    }

    @Override
    public ArrayList<BlockConnection> getOutConnections() {
        return outConnections;
    }

    @Override
    public ArrayList<BlockConnection> getInConnections() {
        return inConnections;
    }

    @Override
    public Rotation getRotation() {
        return rotation;
    }

    @Override
    public ArrayList<Boolean> getOutLinks(){return outLinks;}

    @Override
    public ArrayList<Boolean> getInLinks(){return outLinks;}

    @Override
    public boolean setOutConnection(BlockConnection connection){
        int id = connection.sourceIndex;
        if(!outLinks.get(id)) return false;
        if(!connection.target.setInConnection(connection.reversed()))return false;
        outConnections.set(id, connection);
        return true;
    }

    @Override
    public boolean setInConnection(BlockConnection connection){
        int id = connection.sourceIndex;
        if(!inLinks.get(id)) return false;
        inConnections.set(id, connection);
        return true;
    }

    protected void setOutLink(Rotation direction, boolean value){
        outLinks.set(direction.index, value);
    }
    protected void setInLink(Rotation direction, boolean value){inLinks.set(direction.index, value);}
    protected void setBiLink(Rotation direction, boolean value) {
        setInLink(direction, value);
        setOutLink(direction, value);
    }

    private double blockSize = Block.getDimensions().length();
    private double lineWidth = 10;
    private double lineCenter = lineWidth/2;
    private double middle = blockSize/2;

    private double getX(int index, boolean isIn, double fraction){
        return switch (index){
            case 1 -> blockSize - fraction * blockSize;
            case 3 -> 0 + fraction * blockSize;
            case 0 -> middle + (isIn ? -lineCenter : lineCenter);
            case 2 -> middle + (isIn ? lineCenter : -lineCenter);
            default -> throw new RuntimeException();
        };
    }

    private double getY(int index, boolean isIn, double fraction){
        return getX((index + 3)%4, isIn, fraction);
    }

    protected boolean wrongPathEndpoints(int startId, int endId){
        return !getInLink(startId) || !getOutLink(endId);
    }

    @Override
    public Polyline getPath(int startId, int endId){
        if(wrongPathEndpoints(startId, endId)) throw new PathNotAvailableException();
        var path = new Polyline();
        path.getPoints()
                .addAll(
                getX(startId, true, 0),getY(startId, true, 0),
                getX(startId, true, .4),getY(startId, true, .4),
                getX(endId, false, .4),getY(endId, false, .4),
                getX(endId, false, 0),getY(endId, false, 0)
                );
        return path;
    }

    public static class PathNotAvailableException extends RuntimeException{};

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();

    {
        vehicles.add(new Vehicle(new Vector2(50, 50)));
    }

    public void update(double deltaTime){
        for(var vehicle: vehicles){
            vehicle.updatePosition(deltaTime);
            vehicle.updateSpeed(deltaTime, 1);
        }
    }

    public Vector2 getPosition(){
        return position;
    }

    public Collection<Vehicle> getVehicles(){
        return vehicles;
    }

    public void receiveVehicle(BlockConnection connection, Vehicle vehicle){

    }

}

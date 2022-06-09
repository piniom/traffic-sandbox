package pl.tcs.po.models.blocks;

import pl.tcs.po.models.mobile.Vector2;
import pl.tcs.po.models.mobile.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractBlock implements Block {

    private static int SIDES_COUNT = 4;

    private Rotation rotation;
    private final Vector2 position;
    protected ArrayList<BlockConnection> outConnections;
    protected ArrayList<BlockConnection> inConnections;
    protected ArrayList<Boolean> outLinks;
    protected ArrayList<Boolean> inLinks;

    //TODO: Remove this constructor and fix the backward compatibility issues
    public AbstractBlock(Rotation rotation){
        this(new Vector2(), rotation);
    }

    public AbstractBlock(Vector2 position, Rotation rotation){
        //if(position != null) System.out.println(position.x() + " + " + position.y());
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
        outConnections.set(connection.sourceIndex, null);
        connection.target.getInConnections().set(connection.targetIndex, null);
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

    private double blockSize = Block.getDimensions().width();
    private double lineWidth = 20;
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

    protected boolean checkPathEndpoints(int startId, int endId){
        return !getInLink(startId) || !getOutLink(endId);
    }

    @Override
    public ArrayList<Vector2> getPath(int startId, int endId){
        if(checkPathEndpoints(startId, endId)){
            System.out.println(getName() + startId + endId);
            throw new PathNotAvailableException();
        }
        ArrayList<Vector2> path = new ArrayList<>();
        path.add(new Vector2(getX(startId, true, 0),getY(startId, true, 0)));
        path.add(new Vector2(getX(startId, true, .4),getY(startId, true, .4)));
        path.add(new Vector2(getX(endId, false, .4),getY(endId, false, .4)));
        path.add(new Vector2(getX(endId, false, 0),getY(endId, false, 0)));

        path = new ArrayList<>(path.stream().map(this::localToGlobal).toList());
        return  path;
    }

    public static class PathNotAvailableException extends RuntimeException{};

    protected final ArrayList<Vehicle> vehicles = new ArrayList<>();

    public synchronized void update(long deltaTime){
        for(var vehicle: new ArrayList<>(vehicles)){
            vehicle.update(deltaTime);
        }
    }

    public Vector2 getPosition(){
        return position;
    }

    public Collection<Vehicle> getVehicles(){
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

}

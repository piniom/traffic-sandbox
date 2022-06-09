package pl.tcs.po.models.mobile;

import pl.tcs.po.models.blocks.BlockConnection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vehicle {

    public final double maxSpeed = 30;
    public final double acceleration = 4;
    public final Vector2 dimensions = new Vector2(10, 30);

    private Vector2 position;
    private VectorRadial velocity;
    private final ArrayList<BlockConnection> connections;

    public Vehicle(Vector2 position, VectorRadial velocity, ArrayList<BlockConnection> connections){
        this.position = position;
        this.velocity = velocity;
        this.connections = connections;
    }

    public void updatePosition(long deltaTime){
        position = position.add(velocity.toVector2().scale(deltaTime/1000.0));
    }

    public void updateVelocityDirection(long deltaTime, Vector2 desiredPosition){
        //velocity = desiredPosition.subtract(position).unit().scale(velocity.length());

    }

    public void updateSpeed(long deltaTime, double desiredSpeed){
        velocity = new VectorRadial(desiredSpeed, velocity.radians());
    }

    public double getRotation(){
        return velocity.toVector2().rotationDegrees();
    }

    public Vector2 getPosition(){return position;}

    public List<Vector2> getPolygon(){
        var list = new LinkedList<Vector2>();
        list.add(new Vector2(dimensions.x(), dimensions.y()).scale(.5).rotateAround(new Vector2(), velocity.radians()).add(position));
        list.add(new Vector2(dimensions.x(), -dimensions.y()).scale(.5).rotateAround(new Vector2(), velocity.radians()).add(position));
        list.add(new Vector2(-dimensions.x(), -dimensions.y()).scale(.5).rotateAround(new Vector2(), velocity.radians()).add(position));
        list.add(new Vector2(-dimensions.x(), dimensions.y()).scale(.5).rotateAround(new Vector2(), velocity.radians()).add(position));
        return list;
    }
}

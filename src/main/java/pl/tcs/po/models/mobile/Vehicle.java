package pl.tcs.po.models.mobile;

import javafx.scene.paint.Color;
import pl.tcs.po.models.blocks.Block;
import pl.tcs.po.models.blocks.BlockConnection;
import pl.tcs.po.models.blocks.EmptyBlock;

import java.util.*;

public class Vehicle {

    private static Random r = new Random();

    public final double maxSpeed = 30;
    public final double acceleration = 4;
    public final Vector2 dimensions = new Vector2(10, 30);

    private Vector2 position;
    private VectorRadial velocity;
    private final ArrayList<BlockConnection> connections;
    private ArrayList<Vector2> path = new ArrayList<>();
    private int pathIndex = 0;
    private final Block source;
    public final Color color = Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble());


    public Vehicle(Vector2 position, VectorRadial velocity, ArrayList<BlockConnection> connections){
        this.position = position;
        this.velocity = velocity;
        this.connections = connections;
        this.source = connections.get(0).source;
        for(int i=1;i<connections.size();i++){
            var cur = connections.get(i);
            path.addAll(cur.source.getPath(connections.get(i-1).targetIndex, cur.sourceIndex));
        }

    }

    private Vector2 leap(long deltaTime){
        return velocity.toVector2().scale(deltaTime/1000.0);
    }

    public void updatePosition(long deltaTime){
        position = position.add(leap(deltaTime));
    }

    private Vector2 target = null;

    private boolean reachesTarget(long deltaTime){
        if(target == null) return true;
        if(position.subtract(target).length() < leap(deltaTime).length()){
            return true;
        }
        return false;
    }

    public void update(long deltaTime){
        if(reachesTarget(deltaTime)){
            if(!(pathIndex + 1  < path.size())){
                source.removeVehicle(this);
                return;
            }
            pathIndex++;
            target = path.get(pathIndex);
        }
        velocity = target.subtract(position).toVectorRadial().scaleToLength(30);
        updatePosition(deltaTime);
    }

    public void updateSpeed(long deltaTime, double desiredSpeed){
        velocity = new VectorRadial(desiredSpeed, velocity.radians());
    }

    public double getRotation(){
        return velocity.toVector2().rotationDegrees();
    }

    public Vector2 getPosition(){return position;}

    public List<Vector2> getPolygon(){
        List<Vector2> list = new LinkedList<>();
        list.add(new Vector2(dimensions.x(), dimensions.y()));
        list.add(new Vector2(dimensions.x(), -dimensions.y()));
        list.add(new Vector2(-dimensions.x(), -dimensions.y()));
        list.add(new Vector2(-dimensions.x(), dimensions.y()));
        list = list.stream().map(v -> v.scale(.5).rotateAround(new Vector2(), velocity.radians()).add(position)).toList();
        return list;
    }

    private static final double distanceThreshold = 5;

}

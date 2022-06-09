package pl.tcs.po.models.mobile;

import javafx.scene.paint.Color;
import pl.tcs.po.models.blocks.Block;
import pl.tcs.po.models.blocks.BlockConnection;

import java.util.*;
import java.util.concurrent.Callable;

public class Vehicle {

    private static Random r = new Random();

    public final double maxSpeed = 30;
    public final double acceleration = 4;
    public final Vector2 dimensions = new Vector2(r.nextInt(8,12), r.nextInt(25,35));

    private Vector2 position;
    private VectorRadial velocity;
    private final ArrayList<BlockConnection> connections;
    private ArrayList<Pair> path = new ArrayList<>();
    private int pathIndex = 0;
    private Block source;
    public final Color color = Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble());
    public int priority = r.nextInt();

    private record Pair(Vector2 point, Block addTo){}

    public Vehicle(Vector2 position, VectorRadial velocity, ArrayList<BlockConnection> connections){
        this.position = position;
        this.velocity = velocity;
        this.connections = connections;
        this.source = connections.get(0).source;
        for(int i=1;i<connections.size();i++){
            var cur = connections.get(i);

            var points = cur.source.getPath(connections.get(i-1).targetIndex, cur.sourceIndex);
            path.add(new Pair(points.get(0), cur.target));
            for(int p=1;p<points.size();p++)path.add(new Pair(points.get(p), null));
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

    public void update(long deltaTime, Collection<Vehicle> others){
        if(reachesTarget(deltaTime)){
            if(!(pathIndex + 1  < path.size())){
                source.removeVehicle(this);
                return;
            }
            pathIndex++;
            var pair= path.get(pathIndex);
            target = pair.point;
            if(pair.addTo != null){
                source.removeVehicle(this);
                pair.addTo.addVehicle(this);
                source = pair.addTo;
            }
        }
        velocity = target.subtract(position).toVectorRadial().scaleToLength(velocity.length());
        updateSpeed(deltaTime, others);
        updatePosition(deltaTime);
    }

    private boolean shouldYieldTo(Vehicle other){
        return false;
//        if(other.priority >= this.priority)return false;
//        return !(Math.abs(Math.sin(this.velocity.radians() - other.velocity.radians())) < 0.9);
    }

    public void updateSpeed(long deltaTime, Collection<Vehicle> others){
        double len = velocity.length();
        double diff = Math.max(len * deltaTime / 2000, 4);
        if(others.stream().noneMatch(this::shouldYieldTo))velocity.scaleToLength(Math.min(30, len+diff));
        else velocity = velocity.scaleToLength(Math.max(len-diff, 5));
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
}

package pl.tcs.po.models.mobile;

import java.util.*;

public class PointPath implements Iterable<Vector2> {
    private final ArrayList<Vector2> path;
    public PointPath(){
        this(new Vector2[0]);
    }
    public PointPath(Vector2... points){
        path = new ArrayList<>(Arrays.stream(points).toList());
    }
    public void add(Vector2 point){
        path.add(point);
    }

    @Override
    public Iterator<Vector2> iterator() {
        return path.iterator();
    }
}

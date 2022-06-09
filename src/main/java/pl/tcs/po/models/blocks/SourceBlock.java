package pl.tcs.po.models.blocks;

import pl.tcs.po.models.PathFinder;
import pl.tcs.po.models.mobile.Vector2;
import pl.tcs.po.models.mobile.VectorRadial;
import pl.tcs.po.models.mobile.Vehicle;

import java.util.Date;

public class SourceBlock extends AbstractBlock{
    private static final long spawnInterval = 200;

    private long lastSpawn;

    public SourceBlock(Vector2 position, Rotation rotation) {
        super(position, rotation);
        setBiLink(Rotation.SOUTH, true);
        lastSpawn = new Date().getTime();
    }

    private Vehicle newVehicle(Vector2 localPoint, double radians){
        while (true){
            var path = PathFinder.getRandomPath(this, 3);
            if(path != null){
                return new Vehicle(localToGlobal(localPoint), new VectorRadial(0, radians + getRotation().radians()), path);
            }
        }
    }

    private void addVehicle(){
        vehicles.add(newVehicle(new Vector2(50,45), Math.PI));
        lastSpawn = new Date().getTime();
    }

    @Override
    public void update(long deltaTime){
        if(new Date().getTime() - lastSpawn > spawnInterval)addVehicle();
        super.update(deltaTime);
    }

    @Override
    public String getName() {
        return "source";
    }
}

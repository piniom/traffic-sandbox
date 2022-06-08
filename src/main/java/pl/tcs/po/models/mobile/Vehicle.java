package pl.tcs.po.models.mobile;

public class Vehicle {

    public final double maxSpeed = 30;
    public final Vector2 dimensions = new Vector2(2, 4);

    private Vector2 position;
    private Vector2 velocity;

    public Vehicle(Vector2 position){
        this.position = position;
        this.velocity = new Vector2();
    }

    public void updatePosition(double deltaTime){
        position = position.scale(1.0 + deltaTime);
    }

    public void updateVelocityDirection(double deltaTime, Vector2 desiredPosition){
        velocity = desiredPosition.subtract(position).unit().scale(velocity.length());
    }

    public void updateSpeed(double deltaTime, double desiredSpeed){
        velocity = velocity.scale(desiredSpeed/velocity.length());
    }

    public double getRotation(){
        return velocity.rotationDegrees();
    }

    public Vector2 getPosition(){return position;}
}

package pl.tcs.po.models.mobile;

public record Vector2(double x, double y) {

    public Vector2(){
        this(0.0, 0.0);
    }

    public Vector2 add(Vector2 other){
        return new Vector2(x + other.x(), y + other.y());
    }

    public Vector2 scale(double factor){
        if(length() == 0)return new Vector2();
        return new Vector2(x*factor, y*factor);
    }

    public double length(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2 unit(){
        return this.scale(1/length());
    }

    public Vector2 opposite(){
        return this.scale(-1.0);
    }

    public Vector2 subtract(Vector2 other){
        return add(other.opposite());
    }

    public double rotationRadians(){
        return Math.atan2(y, x);
    }

    public double rotationDegrees(){
        return Math.toDegrees(rotationRadians());
    }

    public double width(){return x;}
    public double height(){return y;}

    public Vector2 rotateAround(Vector2 other, double radians){
        return this.subtract(other).toVectorRadial().rotate(radians).toVector2().add(other);
    }

    public VectorRadial toVectorRadial(){
        return new VectorRadial(length(), Math.PI - Math.atan2(x,y));
    }
}

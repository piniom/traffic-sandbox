package pl.tcs.po.models.mobile;

public record VectorRadial(double length, double radians) {
    public VectorRadial(){
        this(0,0);
    }
    public VectorRadial scale(double factor){
        return new VectorRadial(length * factor, radians);
    }
    public Vector2 toVector2(){
        return new Vector2(length * Math.sin(radians), -length * Math.cos(radians));
    }

    public VectorRadial rotate(double alpha){
        return new VectorRadial(length, this.radians + alpha);
    }
}

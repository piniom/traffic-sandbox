package pl.tcs.po.controllers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pl.tcs.po.models.blocks.Block;
import pl.tcs.po.models.mobile.Vector2;
import pl.tcs.po.models.mobile.Vehicle;

public class VehicleCanvas {

    final Canvas canvas;
    private final GraphicsContext context;
    Vector2 offset = new Vector2();


    public VehicleCanvas(int blocksX, int blocksY) {
        canvas = new Canvas(blocksX* Block.getDimensions().width(),
                blocksY*Block.getDimensions().height());
        context = canvas.getGraphicsContext2D();
    }

    void setOffset(Vector2 offset){
        this.offset = offset;
    }

    void reset(){
        context.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
    }

    void drawVehicle(Vehicle vehicle){
        context.setFill(vehicle.color);
        var dim = vehicle.dimensions;
        //Szymon's pixel problems :)))
        var polygon = vehicle.getPolygon().stream().map(v -> v.add(offset).scale(.99)).toList();
        double[] xs = polygon.stream().mapToDouble(Vector2::x).toArray();
        double[] ys = polygon.stream().mapToDouble(Vector2::y).toArray();
        context.fillPolygon(xs, ys, xs.length);
    }
}

package pl.tcs.po.views;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import pl.tcs.po.models.blocks.AbstractBlock;
import pl.tcs.po.models.blocks.Block;
import pl.tcs.po.models.blocks.BlockConnection;
import pl.tcs.po.models.blocks.Rotation;
import pl.tcs.po.models.mobile.PointPath;

import java.util.function.Function;

public class BlockViewDebug extends BlockView{

    public BlockViewDebug(Block block){
        super(block);
    }

    Polyline pointPathToPolyline(PointPath path){
        var line = new Polyline();
        for(var p : path)line.getPoints().addAll(p.x(), p.y());
        return line;
    }

    Pane getPaths(){
        Pane pane = new StackPane();
        for(int in=0;in<4;in++){
            for(int out=0;out<4;out++){
                try{
                    pane.getChildren().add(new Pane(pointPathToPolyline( block.getPath(in, out))));
                }catch (AbstractBlock.PathNotAvailableException ignored){}
            }
        }
        return pane;
    }

    @Override
    public Node getNode() {
        ImageView node = new ImageView(image);
        Pane pane = new StackPane(
                node,
                getPaths(),
                debugOutLinks(),
                debugInLinks(),
                debugOutConnections(),
                debugInConnections()
        );
        pane.setRotate(block.getRotation().degrees);
        return pane;
    }

    private Pane debugBoiler(double width, double height, double offset, Function<Rotation, Color> colorFunction){
        Pane rectangles = new Pane();
        for(Rotation d : Rotation.values()){
            Rectangle rectangle = getRectangle(d, width, height, offset);
            rectangle.setFill(colorFunction.apply(d));
            rectangles.getChildren().add(rectangle);
        }
        return rectangles;
    }

    private double linkOffset = 10;
    private double linkWidth = 10;
    private double linkHeight = 5;

    private Pane debugOutLinks(){
        return debugBoiler(linkWidth, linkHeight, linkOffset, (d) -> getColor(block.getOutLink(d.index)));
    }

    private Pane debugInLinks(){
        return debugBoiler(linkWidth, linkHeight, -linkOffset, (d) -> getColor(block.getInLink(d.index)));
    }

    private double connectionWidth = 5;
    private double connectionHeight = linkHeight;
    private double connectionOffset = linkOffset + linkWidth - connectionWidth /2.0;

    private Pane debugInConnections(){
        return debugBoiler(connectionWidth, connectionHeight, -connectionOffset,
                (d) -> getColor(block.getInConnection(d.index)));
    }

    private Pane debugOutConnections(){
        return debugBoiler(connectionWidth, connectionHeight, connectionOffset,
                (d) -> getColor(block.getOutConnection(d.index)));
    }


    private Color getColor(boolean value){
        if(value) return Color.LIGHTGREEN;
        else return Color.ORANGE;
    }

    private Color getColor(BlockConnection connection){
        if(connection == null) return Color.RED;
        else return Color.LIGHTGREEN;
    }

    private Rectangle getRectangle(Rotation direction, double width, double height, double offset){
        double centerWidth = image.getWidth()/2.0;
        double centerHeight = image.getHeight()/2.0;
        return switch (direction){
            case NORTH -> new Rectangle( centerWidth-width/2 + offset, 0, width ,height);
            case SOUTH -> new Rectangle( centerWidth-width/2 - offset, image.getHeight()-height, width ,height);
            case WEST -> new Rectangle(0, centerHeight-width/2 - offset, height, width);
            case EAST-> new Rectangle(image.getWidth()-height, centerHeight-width/2 + offset, height, width);
        };
    }
}

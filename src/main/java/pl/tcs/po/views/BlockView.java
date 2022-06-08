package pl.tcs.po.views;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import pl.tcs.po.models.blocks.Block;


public class BlockView{

    protected final Image image;
    protected final Block block;
    protected final ImageLoader imageCache = new ImageLoader();
    protected final Canvas vehiclesCanvas = new Canvas();
    protected final GraphicsContext vehiclesContext = vehiclesCanvas.getGraphicsContext2D();



    public BlockView(Block block){
        this.block = block;
        this.image = imageCache.loadImage(block.getName());
    }

    public Node getNode() {
        Pane pane = new StackPane(new ImageView(image), vehiclesCanvas);
        pane.setRotate(block.getRotation().degrees);
        return pane;
    }

    public void update(){
        for(var v:block.getVehicles()){
            vehiclesContext.fillRect(v.getPosition().x(), v.getPosition().y(), v.dimensions.x()*4, v.dimensions.y()*4);
        }
    }

    public double pixelSize(){
        return image.getWidth();
    }
}

package pl.tcs.po.views;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import pl.tcs.po.models.blocks.Block;


public class BlockView{

    protected final Image image;
    protected final Block block;

    private String getImagePathFromBlockName(){
        return "File:img/" + block.getName() + ".png";
    }

    public BlockView(Block block){
        this.block = block;
        this.image = new Image(getImagePathFromBlockName());
    }

    public Node getNode() {
        Pane pane = new Pane(new ImageView(image));
        pane.setRotate(block.getRotation().degrees);
        return pane;
    }

    public double pixelSize(){
        return image.getWidth();
    }
}

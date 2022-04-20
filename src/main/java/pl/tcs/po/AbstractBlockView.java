package pl.tcs.po;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class AbstractBlockView implements BlockView{

    abstract String getImgPath();

    private final BlockRotation rotation;
    private final ImageView node;

    public AbstractBlockView(Block block, BlockRotation rotation){
        this.rotation = rotation;
        node = new ImageView(new Image(getImgPath()));
    }

    public AbstractBlockView(Block block){
        this(block, BlockRotation.NORTH);
    }

    @Override
    public Node getNode() {
        Pane pane = new Pane(node);
        pane.setRotate(rotation.degrees);
        return pane;
    }

    @Override
    public Node getDebugNode() {
        Rectangle rectangle = new Rectangle(20, 20, 20 ,20);
        rectangle.setFill(Color.RED);
        Pane pane = new Pane(node, rectangle);
        pane.setRotate(rotation.degrees);
        return pane;
    }
}

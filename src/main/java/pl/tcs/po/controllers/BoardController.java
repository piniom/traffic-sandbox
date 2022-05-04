package pl.tcs.po.controllers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import pl.tcs.po.models.Board;
import pl.tcs.po.models.blocks.BlockCreator;
import pl.tcs.po.models.blocks.Rotation;
import pl.tcs.po.views.BlockView;
import pl.tcs.po.views.BlockViewDebug;

public class BoardController {

    private Board board;
    private GridPane grid = new GridPane();
    private boolean isDebug;
    BlockCreator currentBlock = BlockCreator.EMPTY;
    Rotation currentRotation = Rotation.NORTH;
    Text currentText = new Text();

    public BoardController(Board board, boolean isDebug){
        this.board = board;
        this.isDebug = isDebug;
    }
    public BoardController(Board board){this(board, false);}

    public Parent getParentView(){
        setConstraints(grid);
        renderBlocks();
        return grid;
    }

    private void setConstraints(GridPane grid) {
        setRowConstraints(grid);
        setColumnConstraints(grid);
    }

    private void renderBlocks(int fromColumn, int toColumn, int fromRow, int toRow) {
        for(int c=fromColumn;c<toColumn;c++){
            for(int r=fromRow;r<toRow;r++) grid.add(getBlockViewNode(c, r), c, r);
        }
    }

    public void updateBlock(int column, int row){
        if(isDebug){
            renderBlocks();
            return;
        }
        grid.add(getBlockViewNode(column, row), column, row);
    }

    private void setColumnConstraints(GridPane grid) {
        for(int c=0;c< board.getWidth();c++){
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setMaxWidth(99);
            grid.getColumnConstraints().add(constraints);
        }
    }

    private void setRowConstraints(GridPane grid) {
        for(int r=0;r< board.getHeight();r++){
            RowConstraints constraints = new RowConstraints();
            constraints.setMaxHeight(99);
            grid.getRowConstraints().add(constraints);
        }
    }

    private Node getBlockViewNode(int column, int row){
        Node node;
        if(isDebug) node = new BlockViewDebug(board.getBlock(column, row)).getNode();
        else node = new BlockView(board.getBlock(column, row)).getNode();
        node.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.PRIMARY)){
                board.setBlock(column, row, currentBlock.getNewBlock(currentRotation));
                updateBlock(column, row);
            }
        });
        return node;
    }

    public void toggleDebug(){
        isDebug = !isDebug;
        renderBlocks();
    }

    void renderBlocks(){
        renderBlocks(1 ,board.getWidth()-1, 1, board.getHeight()-1);
    }
}

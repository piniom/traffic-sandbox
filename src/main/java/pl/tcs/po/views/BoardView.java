package pl.tcs.po.views;

import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import pl.tcs.po.models.Board;

public class BoardView {

    private Board board;

    public BoardView(Board board){
        this.board = board;
    }

    public Parent getParentView(){
        var grid = new GridPane();
        setConstraints(grid);
        renderBlocks(grid, 1 ,board.getWidth()-1, 1, board.getHeight()-1);
        return grid;
    }

    private void setConstraints(GridPane grid) {
        setRowConstraints(grid);
        setColumnConstraints(grid);
    }

    private void renderBlocks(GridPane grid, int fromColumn, int toColumn, int fromRow, int toRow) {
        for(int c=fromColumn;c<toColumn;c++){
            for(int r=fromRow;r<toRow;r++) grid.add(new BlockView(board.getBlock(c,r)).getNode(), c, r);
        }
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
}

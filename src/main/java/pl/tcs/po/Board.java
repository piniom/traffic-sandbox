package pl.tcs.po;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class Board {

    Parent getBoardScene(){
        var grid = new GridPane();
        for(int c=0;c<10;c++){
            for(int r=0;r<10;r++)grid.add(BlockFactory.getRandomBlock().getView().getNode(), c, r);
        }
        return grid;
    }
}

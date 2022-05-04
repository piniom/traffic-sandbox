package pl.tcs.po.models;

import pl.tcs.po.models.blocks.*;

public class Board {

    private Block[][] blocks;
    private final int width;
    private final int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        blocks = new Block[width][height];
        fillBlocksEmpty();
        //setBlocksRandom();
    }

    private void fillBlocksEmpty(){
        for(int c=0;c<width;c++){
            for(int r=0;r<height;r++)blocks[c][r] = new EmptyBlock();
        }
    }

    private void setBlocksRandom(){
        for(int c=1;c<width-1;c++){
            for(int r=1;r<height-1;r++)setBlock(c,r,BlockFactory.getRandomBlock());
        }
    }

    public Block getBlock(int column, int row){return blocks[column][row];}

    private int getTargetIndex( Block target, int sourceIndex){
        return (10 + sourceIndex - target.getRotation().index)%4;
    }

    private Block getNeighbourBlock(int myColumn, int myRow, int index){
        int c = switch (index){
            case 1 -> 1;
            case 3 -> -1;
            default -> 0;
        };
        int r = switch (index){
            case 0 -> -1;
            case 2 -> 1;
            default -> 0;
        };
        return blocks[myColumn + c][myRow + r];
    }

    public void setBlock(int column, int row, Block block){
        blocks[column][row] = block;
        for(int i=0;i<4;i++){
            int directionIndex = (i + block.getRotation().index) % 4;
            Block target = getNeighbourBlock(column, row, directionIndex);
            int targetIndex = getTargetIndex(target, directionIndex);
            block.setOutConnection(i, new BlockConnection(target, targetIndex));
            target.setOutConnection(targetIndex, new BlockConnection(block, i));
        }
    }

    public int getWidth(){return width;}
    public int getHeight(){return height;}
}

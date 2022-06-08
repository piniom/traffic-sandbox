package pl.tcs.po.models;

import pl.tcs.po.models.blocks.*;
import pl.tcs.po.models.mobile.Vehicle;

import java.util.*;

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
        //System.out.println("New " + block.getName() + " Block: ["+column+", "+row+"]");
        blocks[column][row] = block;
        for(int i=0;i<4;i++){
            int directionIndex = (i + block.getRotation().index) % 4;
            Block target = getNeighbourBlock(column, row, directionIndex);
            int targetIndex = getTargetIndex(target, directionIndex);
            block.setOutConnection(new BlockConnection(block, i, target, targetIndex));
            target.setOutConnection(new BlockConnection(target, targetIndex, block, i));
        }
    }

    public int getWidth(){return width;}
    public int getHeight(){return height;}

    private HashSet<Vehicle> vehicles = new HashSet<>();

    public HashSet<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public void updateVehicles(double deltaTime){
        vehicles.forEach(vehicle -> vehicle.updatePosition(deltaTime));
    }

    public ArrayList<BlockConnection> getPath(Block source, Block target){
        if(source.equals(target))return new ArrayList<>();

        HashMap<Block, BlockConnection> visited = new HashMap<>();
        Queue<Block> queue = new LinkedList<>();


        queue.add(source);

        boolean pathFound = false;

        while (!queue.isEmpty()){
            Block current = queue.remove();
            if(current.equals(target)){
                pathFound = true;
                break;
            }
            for(BlockConnection connection : current.getOutConnections()){
                if(connection == null) continue;
                if(visited.containsKey( connection.target))continue;
                visited.put(connection.target, connection);
                queue.add(connection.target);
            }
        }

        if(!pathFound) return null;

        ArrayList<BlockConnection> output = new ArrayList<>();

        var current = visited.get(target);

        while(current.source != source){
            output.add(current);
            current = visited.get(current.source);
        }

        output.add(current);

        Collections.reverse(output);

        return output;
    }

    public ArrayList<BlockConnection> getPath(int sourceX, int sourceY, int targetX, int targetY){
        return getPath(blocks[sourceX][sourceY], blocks[targetX][targetY]);
    }

    public boolean correctCoords(int column, int row) {
        return column >= 0 && column < getWidth() && row >= 0 && row < getHeight();
    }
}

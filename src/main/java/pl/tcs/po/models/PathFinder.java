package pl.tcs.po.models;

import pl.tcs.po.models.blocks.Block;
import pl.tcs.po.models.blocks.BlockConnection;

import java.util.*;

public class PathFinder {
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
}

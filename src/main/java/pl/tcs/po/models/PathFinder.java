package pl.tcs.po.models;

import pl.tcs.po.models.blocks.Block;
import pl.tcs.po.models.blocks.BlockConnection;

import java.util.*;


public class PathFinder {
    interface FinalCondition {
        boolean condition(Block current);
    }

    public static ArrayList<BlockConnection> getPath(Block source, Block target){
        if(source.equals(target))return new ArrayList<>();
        return getPath(source, current -> current.equals(target));
    }

    public static ArrayList<BlockConnection> getRandomPath(Block source, int probableLength){
        return getPath(source, current -> new Random().nextInt(probableLength)==0);
    }

    public static ArrayList<BlockConnection> getPath(Block source, int length){
        var r = new Random();
        length += r.nextInt(length);
        ArrayList<BlockConnection> out = new ArrayList<>();
        var cur = source;
        for(int i=0;i<length;i++){
            while(true){
                var connection = cur.getOutConnection(r.nextInt(4));
                if(connection != null){
                    out.add(connection);
                    cur = connection.target;
                    break;
                }
            }
        }
        return out;
    }

    public static ArrayList<BlockConnection> getPath(Block source, FinalCondition condition){
        HashMap<Block, BlockConnection> visited = new HashMap<>();
        Queue<Block> queue = new LinkedList<>();


        queue.add(source);
        visited.put(source, null);

        boolean pathFound = false;

        Block target = null;

        while (!queue.isEmpty()){
            Block current = queue.remove();
            if(condition.condition(current)){
                target = current;
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
        if(visited.isEmpty())return new ArrayList<>();

        ArrayList<BlockConnection> output = new ArrayList<>();

        var current = visited.get(target);

        while(current != null && current.source != source){
            output.add(current);
            current = visited.get(current.source);
        }

        if(current == null)return null;

        output.add(current);

        Collections.reverse(output);

        return output;
    }
}

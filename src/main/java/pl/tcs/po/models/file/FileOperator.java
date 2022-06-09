package pl.tcs.po.models.file;

import pl.tcs.po.models.Board;
import pl.tcs.po.models.blocks.Block;
import pl.tcs.po.models.blocks.BlockFactory;
import pl.tcs.po.models.blocks.Rotation;
import pl.tcs.po.models.mobile.Vector2;

import java.util.Arrays;

// TODO: finish implementation
public class FileOperator {
    public static String boardToString(Board board) {
        return board.toString();
    }

    public static Board stringToBoard(String string) throws NumberFormatException, FileFormatException, IllegalArgumentException {
        String[] strings = string.split(",");
        int width = Integer.parseInt(strings[0]);
        int height = Integer.parseInt(strings[1]);
        String[] blocks = strings[2].split(" ");

        if(blocks.length - 1 != width*height) throw new FileFormatException();

        Board board = new Board(width, height);
        for(int i = 1; i < width-1; i++) {
            for(int j = 1; j < height-1; j++) {
                String[] currentBlock = blocks[i*height+j+1].split("-");
                Rotation rotation = Rotation.fromString(currentBlock[1]);
                Vector2 position = Vector2.fromString(currentBlock[2]);
                Block block = BlockFactory.fromName(currentBlock[0], rotation, position);
                board.setBlock(i, j, block);
            }
        }


        return board;
    }

    private static class FileFormatException extends Exception {
    }
}

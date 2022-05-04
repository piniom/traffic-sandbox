package pl.tcs.po.views;

import javafx.scene.image.Image;

import java.util.HashMap;

public class ImageLoader {

    private final HashMap<String, Image> preloaded = new HashMap<>();

    public Image loadImage(String blockName){
        String path = getImagePathFromBlockName(blockName);
        if(preloaded.containsKey(path))return preloaded.get(path);
        Image image = new Image(path);
        preloaded.put(path, image);
        return image;
    }

    private String getImagePathFromBlockName(String name){
        return "File:img/" + name + ".png";
    }
}

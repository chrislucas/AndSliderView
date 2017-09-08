package appcode.xploreviewpager.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r028367 on 08/09/2017.
 */

public class ImageReaderFromAssetsFolder {

    public static List<Bitmap> getImages(Context context, List<String> pathfiles) throws IOException {
        List<Bitmap> bitmaps = new ArrayList<>();
        for(String pathfile : pathfiles) {
            Bitmap bitmap = getImage(context, pathfile);
            bitmaps.add(bitmap);
        }
        return bitmaps;
    }


    public static Bitmap getImage(Context context, String pathfile) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream inputStream = manager.open(pathfile);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return bitmap;
    }


}

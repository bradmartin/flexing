package net.bradmartin.flexing;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class Images {
    public Bitmap getBitmapFromImageView(ImageView value) {
        // if not instance of imageview throw an exception
        if (!(value instanceof ImageView)) {
            throw new IllegalArgumentException("Value passed to getBitmapFromImageView is not of type ImageView");
        }

        BitmapDrawable drawable = (BitmapDrawable) value.getDrawable();
        return drawable.getBitmap();
    }
}

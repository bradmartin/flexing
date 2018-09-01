package net.bradmartin.flexing

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.hardware.camera2.CameraMetadata
import android.net.Uri
import android.support.media.ExifInterface
import android.util.Log
import android.widget.ImageView
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

const val TAG = "Flexing.Images"

fun getBitmapFromImageView(value: ImageView): Bitmap {
    // if not instance of ImageView throw an exception
    if (value !is ImageView) throw IllegalArgumentException("Value passed to getBitmapFromImageView is not of type ImageView")

    val drawable = value.drawable as BitmapDrawable
    return drawable.bitmap
}

/**
 * Returns the exif data from the camera byte array
 */
fun getOrientationFromBytes(data: ByteArray, cameraId: Int): Int {
    val inputStream = ByteArrayInputStream(data)
    val exif = android.support.media.ExifInterface(inputStream)
    var orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

    try {
        inputStream.close()
    } catch (error: IOException) {
        Log.e(TAG, "Error closing ByteArrayInputStream $error")
    }

    if (cameraId == CameraMetadata.LENS_FACING_BACK) {
        when (orientation) {
            ExifInterface.ORIENTATION_NORMAL -> orientation = ExifInterface.ORIENTATION_FLIP_HORIZONTAL
            ExifInterface.ORIENTATION_ROTATE_180 -> orientation = ExifInterface.ORIENTATION_FLIP_VERTICAL
            ExifInterface.ORIENTATION_ROTATE_90 -> orientation = ExifInterface.ORIENTATION_TRANSVERSE
        }
    }

    Log.i(TAG, "Orientation $orientation")
    return orientation
}

fun saveImageFile(context: Context, file: File, data: ByteArray, saveToGallery: Boolean?) {
    try {
        val fos = FileOutputStream(file)
        fos.write(data)
        fos.close()

        // if we are saving to gallery
        if (saveToGallery == true) {
            val exifInterface = ExifInterface(file.path)
            val orientation = exifInterface.getAttribute("Orientation")
            Log.i(TAG, "orientation $orientation")
            val contentUri = Uri.fromFile(file)
            Log.i(TAG, "contentUri $contentUri")
            val mediaScanIntent = android.content.Intent(
                    "android.intent.action.MEDIA_SCANNER_SCAN_FILE",
                    contentUri
            )

            Log.i(TAG, "Sending broadcast for Intent $mediaScanIntent")
            context.sendBroadcast(mediaScanIntent)
        }
    } catch (error: IOException) {
        Log.e(TAG, "Error saveImageToDisk $error")
        throw IOException("Error writing File to disk: $error")
    }
}

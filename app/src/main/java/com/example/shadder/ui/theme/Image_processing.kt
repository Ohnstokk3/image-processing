package com.example.shadder.ui.theme

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.example.shadder.R

@Composable
fun test() {
    val context = LocalContext.current
    val imageg = BitmapFactory.decodeResource(context.resources, R.drawable.apps_11001_14495311847124170_004a386e_eb07_4903_b17d_bba1cc3df02e)

    val newBit = Bitmap.createBitmap(imageg.width, imageg.height, imageg.config)

    for (x in 0 until imageg.width) {
        for (y in 0 until imageg.height) {
            val pixel = imageg.getPixel(x, y)
            val alpha = Color.alpha(pixel)

            // Modify pixel color values here (example: make it grayscale)
            val red = Color.red(pixel)
            val green = Color.green(pixel)
            val blue = Color.blue(pixel)
            val grayscale = (red + green + blue) / 3

            // Create the new pixel with modified values
            val newPixel = Color.argb(alpha, green, blue, grayscale)
            newBit.setPixel(x, y, newPixel)
        }
    }

// Convert to ImageBitmap for display
    val convert = newBit.asImageBitmap()

    Column {
        Image(bitmap = convert, contentDescription = "Description of the image")
    }

}